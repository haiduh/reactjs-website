package com.github.group37.roadmap.service;


import com.github.group37.roadmap.errors.OtpVerificationException;
import com.github.group37.roadmap.errors.ResourceNotFoundException;
import com.github.group37.roadmap.percistance.models.StudentDao;
import com.github.group37.roadmap.other.StudentRequest;
import com.github.group37.roadmap.percistance.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean verifyOtp(String email, String otp) {
        Optional<StudentDao> student = studentRepository.findByEmail(email);
        if (student.isPresent()) {
            String storedOtp = student.get().getOtp().trim().toLowerCase();
            String providedOtp = otp.trim().toLowerCase();

            // Log email and OTP received by the backend
            log.debug("Received email for OTP verification: {}", email);
            log.debug("Received OTP for email {} is: {}", email, providedOtp);

            if (storedOtp.equals(providedOtp)) {
                log.debug("OTP verification successful for email: {}", email);
                return true;
            } else {
                log.debug("Incorrect OTP received for email: {}", email);
                throw new OtpVerificationException("Incorrect OTP. Please try again.");
            }
        } else {
            log.debug("Student not found for email: {}", email);
            throw new OtpVerificationException("Student not found.");
        }
    }

    public void registerStudent(StudentRequest studentRequest, String otp) {
        String encodedPassword = passwordEncoder.encode(studentRequest.password());

        StudentDao studentDao = new StudentDao();
        studentDao.setUsername(studentRequest.username());
        studentDao.setEmail(studentRequest.email());
        studentDao.setPassword(encodedPassword);
        studentDao.setOtp(otp); // Save the OTP

        try {
            studentRepository.save(studentDao);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Duplicate username or other database-related issue", e);
        } catch (Exception e) {
            throw new RuntimeException("Error during student registration: " + e.getMessage(), e);
        }
    }

    // Other methods for StudentService (e.g., update, delete, get) can be added here
    public StudentDao updateStudent(Integer id, StudentRequest updatedStudentRequest) {
        StudentDao existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

        // Update existingStudent properties
        existingStudent.setEmail(updatedStudentRequest.email());

        studentRepository.save(existingStudent);
        return existingStudent;
    }



    public List<StudentDao> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<StudentDao> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    public void deleteStudentById(Integer id) {
        studentRepository.deleteById(id);
    }

    public Optional<StudentDao> getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }
    public boolean authenticateStudent(String email, String password) {
        Optional<StudentDao> student = studentRepository.findByEmail(email);

        return student.isPresent() && passwordEncoder.matches(password, student.get().getPassword());
    }

}