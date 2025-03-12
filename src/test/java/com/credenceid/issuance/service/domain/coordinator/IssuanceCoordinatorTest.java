package com.credenceid.issuance.service.domain.coordinator;

import com.credenceid.issuance.service.domain.model.Application;
import com.credenceid.issuance.service.domain.model.DigitalIdResult;
import com.credenceid.issuance.service.domain.usecase.DeliverDigitalIdUseCase;
import com.credenceid.issuance.service.domain.usecase.GenerateDigitalIdUseCase;
import com.credenceid.issuance.service.domain.usecase.GetApplicationUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class IssuanceCoordinatorTest {

    @Mock
    private GetApplicationUseCase getApplicationUseCase;

    @Mock
    private GenerateDigitalIdUseCase generateDigitalIdUseCase;

    @Mock
    private DeliverDigitalIdUseCase deliverDigitalIdUseCase;

    @InjectMocks
    private IssuanceCoordinatorImpl issuanceCoordinator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIssueDigitalId() {
        // Arrange
        Application mockApplication = mock(Application.class);
        DigitalIdResult mockDigitalIdResult = mock(DigitalIdResult.class);

        when(getApplicationUseCase.execute()).thenReturn(mockApplication);
        when(generateDigitalIdUseCase.execute(mockApplication)).thenReturn(mockDigitalIdResult);

        // Act
        issuanceCoordinator.issueDigitalId();

        // Assert
        verify(getApplicationUseCase, times(1)).execute();
        verify(generateDigitalIdUseCase, times(1)).execute(mockApplication);
        verify(deliverDigitalIdUseCase, times(1)).execute(mockDigitalIdResult);
    }

    @Test
    public void testIssueDigitalId_ExceptionInGetApplication() {
        // Arrange
        when(getApplicationUseCase.execute()).thenThrow(new RuntimeException("Error getting application"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            issuanceCoordinator.issueDigitalId();
        });

        verify(getApplicationUseCase, times(1)).execute();
        verify(generateDigitalIdUseCase, never()).execute(any());
        verify(deliverDigitalIdUseCase, never()).execute(any());
    }

    @Test
    public void testIssueDigitalId_ExceptionInGenerateDigitalId() {
        // Arrange
        Application mockApplication = mock(Application.class);

        when(getApplicationUseCase.execute()).thenReturn(mockApplication);
        when(generateDigitalIdUseCase.execute(mockApplication)).thenThrow(new RuntimeException("Error generating digital ID"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            issuanceCoordinator.issueDigitalId();
        });

        verify(getApplicationUseCase, times(1)).execute();
        verify(generateDigitalIdUseCase, times(1)).execute(mockApplication);
        verify(deliverDigitalIdUseCase, never()).execute(any());
    }

    @Test
    public void testIssueDigitalId_ExceptionInDeliverDigitalId() {
        // Arrange
        Application mockApplication = mock(Application.class);
        DigitalIdResult mockDigitalIdResult = mock(DigitalIdResult.class);

        when(getApplicationUseCase.execute()).thenReturn(mockApplication);
        when(generateDigitalIdUseCase.execute(mockApplication)).thenReturn(mockDigitalIdResult);
        doThrow(new RuntimeException("Error delivering digital ID")).when(deliverDigitalIdUseCase).execute(mockDigitalIdResult);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            issuanceCoordinator.issueDigitalId();
        });

        verify(getApplicationUseCase, times(1)).execute();
        verify(generateDigitalIdUseCase, times(1)).execute(mockApplication);
        verify(deliverDigitalIdUseCase, times(1)).execute(mockDigitalIdResult);
    }

}
