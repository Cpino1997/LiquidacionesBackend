package cl.pinolabs.springreact.security.modelos.dominio.dto;

import java.util.Date;

public record ErrorMessage(int statusCode, Date timestamp, String message, String description) {
}