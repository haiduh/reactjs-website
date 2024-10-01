package com.github.group37.roadmap.controllers;

import com.github.group37.roadmap.other.StudentRequest;
import com.github.group37.roadmap.percistance.models.StudentDao;
import com.github.group37.roadmap.securityConfig.JwtTokenProvider;
import com.github.group37.roadmap.service.StudentService;
import java.lang.String;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@CrossOrigin
@RequestMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    private final StudentService studentService;
    private final JwtTokenProvider jwtTokenProvider;
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public StudentController(StudentService studentService, JwtTokenProvider jwtTokenProvider, JavaMailSender javaMailSender) {
        this.studentService = studentService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.javaMailSender = javaMailSender;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerStudent(@RequestBody StudentRequest studentRequest) {
        try {
            // Generate OTP
            String otp = generateOTP();
            System.out.println("Generated OTP: " + otp);

            // Save user data and OTP to the database
            studentService.registerStudent(studentRequest, otp);

            // Send OTP to user's email
            sendEmail(studentRequest.email(), otp);

            return ResponseEntity.ok("Student registered successfully. An OTP has been sent to your email.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error during student registration: Duplicate username or other database-related issue");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error during student registration: " + e.getMessage());
        }
    }


    private String generateOTP() {
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
    }
    private void sendEmail(String recipientEmail, String otp) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(recipientEmail);
            message.setSubject("Email Verification OTP");
            message.setText("Your OTP for email verification is: " + otp);
            message.setFrom(senderEmail);
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            throw new RuntimeException("Error sending email: " + e.getMessage());
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        try {
            boolean isOtpValid = studentService.verifyOtp(email, otp);
            if (isOtpValid) {
                return ResponseEntity.ok("OTP verification successful. Redirect to login page.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Code verification not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error during OTP verification: " + e.getMessage());
        }
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginStudent(@RequestBody StudentRequest studentRequest) {
        try {
            if (studentService.authenticateStudent(studentRequest.email(), studentRequest.password())) {
                String userEmail = studentRequest.email();
                String jwtToken = jwtTokenProvider.generateToken(userEmail);
                System.out.println("Generated Token: " + jwtToken);
                return ResponseEntity.ok(Collections.singletonMap("token", jwtToken));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error during login: " + e.getMessage());
        }
    }

    @GetMapping
    public List<StudentDao> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDao> getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDao> updateStudent(@PathVariable Integer id, @RequestBody StudentRequest updatedStudentRequest) {
        try {
            StudentDao updatedStudent = studentService.updateStudent(id, updatedStudentRequest);
            return ResponseEntity.ok(updatedStudent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Integer id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok("Student deleted successfully");
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<List<StudentDao>> getStudentsByEmail(@RequestParam String email) {
        Optional<StudentDao> student = studentService.getStudentByEmail(email);
        if (student.isPresent()) {
            return ResponseEntity.ok(Collections.singletonList(student.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}