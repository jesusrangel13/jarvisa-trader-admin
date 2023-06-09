package com.jarl.trading.admin.jarvis.bot.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

	public static final String ERROR_DETECTADO = "Finaliza consulta con codigo de error";
	public static final String MENSAJE_404 = "No se encuentra cliente con el identificador";
	public static final String MENSAJE_400 = "Error de solicitud";
	public static final String MENSAJE_500 = "Error de servidor";

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> methodArgumentNotValidException(HttpServletRequest request,
			MethodArgumentNotValidException e) {
		log.info("Se detecta exception MethodArgumentNotValidException");
		log.info(MENSAJE_400);
		log.error(e.getLocalizedMessage());
		// return error info object with standard json
		ErrorInfo errorInfo = new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(),
				request.getRequestURI());
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorInfo> handleIllegalArgumentException(HttpServletRequest request, Exception e) {
		log.info("Se detecta exception IllegalArgumentException");
		log.info(MENSAJE_400);
		log.error(e.getLocalizedMessage());
		ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI());
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorInfo> handleUserNotFoundException(HttpServletRequest request, NoSuchElementException e) {
		log.info("Se detecta exception NoSuchElementException");
		log.info(MENSAJE_404);
		log.error(e.getLocalizedMessage());
		ErrorInfo errorInfo = new ErrorInfo(HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI());
		return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handleAllExceptions(HttpServletRequest request, Exception e) {
		log.info("Se detecta exception", e.getCause());
		log.info(ERROR_DETECTADO);
		log.error(e.getLocalizedMessage());
		ErrorInfo errorInfo = new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(),
				request.getRequestURI());
		return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
