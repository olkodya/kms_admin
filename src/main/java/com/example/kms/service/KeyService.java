package com.example.kms.service;

import com.example.kms.entity.Audience;
import com.example.kms.entity.Key;
import com.example.kms.form.KeyForm;
import com.example.kms.repository.AudienceRepository;
import com.example.kms.repository.KeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeyService {
    private final KeyRepository keyRepository;
    private final AudienceRepository audienceRepository;

    public Key createKey(KeyForm form){
        Audience audience = audienceRepository.findById(form.getAudience_id())
                .orElseThrow(() -> new RuntimeException("Not found audience with id = " + form.getAudience_id()));
        return keyRepository.save(new Key(audience, form.getKey_state(), form.getMain()));
    }

    public List<Key> getAllKeys(){
        return keyRepository.findAll();
    }

    public Key getKeyById(Integer id){
        return keyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found key with id = " + id));
    }

    public Key getKeyByQR(String QR){
        return keyRepository.findByQR(QR);
    }

    public Key updateKey(Integer id, KeyForm form){
        Key key = keyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found key with id = " + id));
        if (form.getAudience_id() != null) {
            Audience audience = audienceRepository.findById(form.getAudience_id())
                .orElseThrow(() -> new RuntimeException("Not found audience with id = " + form.getAudience_id()));
            key.setAudience(audience);
        }
        if (form.getKey_state() != null)
            key.setKey_state(form.getKey_state());
        if (form.getMain() != null) {
            key.setMain(form.getMain());
        }
        return keyRepository.save(key);
    }

    public Key generateQRForKey(Integer id) {
        Key key = keyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found key with id = " + id));
        String hash = "{key_id = " + key.getKey_id() + ", auditory_id = " + key.getAudience().getAudience_id() + "}";
        key.setQR(hash);
        return keyRepository.save(key);
    }
}
