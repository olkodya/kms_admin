package com.example.kms.service;

import com.example.kms.entity.Image;
import com.example.kms.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public static final int BITE_SIZE = 4 * 1024;

    public static byte[] compressImage(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[BITE_SIZE];

        while(!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp,0, size);
        }

        outputStream.close();
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) throws DataFormatException, IOException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[BITE_SIZE];

        while (!inflater.finished()) {
            int count = inflater.inflate(tmp);
            outputStream.write(tmp, 0, count);
        }

        outputStream.close();
        return outputStream.toByteArray();
    }

    public Image uploadImage(MultipartFile imageFile) throws IOException {
        var image = Image.builder()
                .name(imageFile.getOriginalFilename())
                .type(imageFile.getContentType())
                .data(compressImage(imageFile.getBytes()))
                .build();
        return imageRepository.save(image);
    }

    public byte[] getImageById(Integer id) {
        Optional<Image> dbImage = imageRepository.findById(id);
        return dbImage.map(image -> {
            try {
                return decompressImage(image.getData());
            } catch (DataFormatException | IOException exception) {
                throw new ContextedRuntimeException("Error downloading an image", exception)
                        .addContextValue("Image id", id);
            }
        }).orElse(null);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public void deleteImageById(Integer id) {
        imageRepository.deleteById(id);
    }

}
