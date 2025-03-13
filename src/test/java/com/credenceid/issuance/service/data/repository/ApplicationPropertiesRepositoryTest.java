package com.credenceid.issuance.service.data.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ApplicationPropertiesRepositoryTest {
    @Autowired
    private ApplicationPropertiesRepository repo;

    @Test
    public void test_getting_digest_algorithm_value() {
        // Given

        // When
        assertThat(repo.getDigestAlgorithm()).isEqualTo("SHA-256");

        // Then
    }
}
