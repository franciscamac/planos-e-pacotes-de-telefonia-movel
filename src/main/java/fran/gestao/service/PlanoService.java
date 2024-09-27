package fran.gestao.service;

import fran.gestao.model.Plano;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PlanoService {
    private final String BASE_URL = "http://localhost:8080/api/planos";

    @Autowired
    private RestTemplate restTemplate;

    public List<Plano> listarPlanos() {
        Plano[] planos = restTemplate.getForObject(BASE_URL, Plano[].class);
        return Arrays.asList(planos);
    }

    public Plano buscarPlano(Long id) {
        return restTemplate.getForObject(BASE_URL + "/" + id, Plano.class);
    }

    public Plano salvarPlano(Plano plano) {
        return restTemplate.postForObject(BASE_URL, plano, Plano.class);
    }

    public void atualizarPlano(Long id, Plano plano) {
        restTemplate.put(BASE_URL + "/" + id, plano);
    }

    public void deletarPlano(Long id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }
}
