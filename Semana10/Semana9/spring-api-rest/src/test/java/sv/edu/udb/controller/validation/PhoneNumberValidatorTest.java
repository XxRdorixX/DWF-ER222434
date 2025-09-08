// Rodrigo Escobar Semana10
package sv.edu.udb.controller.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import jakarta.validation.Payload;


import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberValidatorTest {

    private PhoneNumberValidator validator;

    @BeforeEach
    void setUp() {
        validator = new PhoneNumberValidator();
        // Inicializamos con el patrón definido en la anotación (ejemplo: ####-####)
        PhoneNumber phoneNumber = new PhoneNumber() {
            @Override
            public String message() {
                return "Invalid phone number";
            }

            @Override
            public String pattern() {
                return "^\\d{4}-\\d{4}$";
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return PhoneNumber.class;
            }

            @Override
            public Class<? extends Payload>[] payload() {
                return new Class[0];
            }
        };
        validator.initialize(phoneNumber);
    }

    @Test
    @DisplayName("Valid phone number should pass validation")
    void shouldReturnTrue_When_PhoneNumberIsValid() {
        assertTrue(validator.isValid("1234-5678", null));
    }

    @Test
    @DisplayName("Invalid phone number should fail validation")
    void shouldReturnFalse_When_PhoneNumberIsInvalid() {
        assertFalse(validator.isValid("12345678", null));  // Falta el guion
        assertFalse(validator.isValid("abcd-1234", null)); // Letras no son válidas
        assertFalse(validator.isValid("+50312345678", null)); // Formato diferente
    }

    @Test
    @DisplayName("Null value should be invalid")
    void shouldReturnFalse_When_PhoneNumberIsNull() {
        assertFalse(validator.isValid(null, null));
    }
}
