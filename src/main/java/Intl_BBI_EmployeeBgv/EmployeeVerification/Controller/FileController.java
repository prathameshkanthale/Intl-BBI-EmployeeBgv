package Intl_BBI_EmployeeBgv.EmployeeVerification.Controller;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.AadharProof;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.Resume;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.ProfilePhoto;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Repository.AadharProofRepository;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Repository.ResumeRepository;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Repository.ProfilePhotoRepository;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/files")
public class FileController {

    private final AadharProofRepository aadharProofRepository;
    private final ResumeRepository resumeRepository;
    private final ProfilePhotoRepository profilePhotoRepository;

    public FileController(AadharProofRepository aadharProofRepository, ResumeRepository resumeRepository, ProfilePhotoRepository profilePhotoRepository) {
        this.aadharProofRepository = aadharProofRepository;
        this.resumeRepository = resumeRepository;
        this.profilePhotoRepository = profilePhotoRepository;
    }

    // ** View Profile Photo in Browser **
    @GetMapping("/view/profile-photo/{userId}")
    public ResponseEntity<ByteArrayResource> viewProfilePhoto(@PathVariable Long userId) {
        Optional<ProfilePhoto> profilePhoto = profilePhotoRepository.findById(userId);
        if (profilePhoto.isPresent()) {
            byte[] imageData = profilePhoto.get().getPhotoData();

            // Detect MIME type dynamically (JPEG or PNG)
            MediaType mediaType = imageData[0] == (byte) 0x89 ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;

            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(new ByteArrayResource(imageData));
        }
        return ResponseEntity.notFound().build();
    }

    // ** Download Profile Photo **
    @GetMapping("/download/profile-photo/{userId}")
    public ResponseEntity<ByteArrayResource> downloadProfilePhoto(@PathVariable Long userId) {
        Optional<ProfilePhoto> profilePhoto = profilePhotoRepository.findById(userId);
        if (profilePhoto.isPresent()) {
            byte[] imageData = profilePhoto.get().getPhotoData();
            MediaType mediaType = imageData[0] == (byte) 0x89 ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"profile_photo.jpg\"")
                    .contentType(mediaType)
                    .body(new ByteArrayResource(imageData));
        }
        return ResponseEntity.notFound().build();
    }

    // ** View Aadhar Proof in Browser **
    @GetMapping("/view/aadhar/{userId}")
    public ResponseEntity<ByteArrayResource> viewAadhar(@PathVariable Long userId) {
        Optional<AadharProof> aadharProof = aadharProofRepository.findById(userId);
        if (aadharProof.isPresent()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new ByteArrayResource(aadharProof.get().getAadharData()));
        }
        return ResponseEntity.notFound().build();
    }

    // ** Download Aadhar Proof **
    @GetMapping("/download/aadhar/{userId}")
    public ResponseEntity<ByteArrayResource> downloadAadhar(@PathVariable Long userId) {
        Optional<AadharProof> aadharProof = aadharProofRepository.findById(userId);
        if (aadharProof.isPresent()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"aadhar.pdf\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new ByteArrayResource(aadharProof.get().getAadharData()));
        }
        return ResponseEntity.notFound().build();
    }

    // ** View Resume in Browser **
    @GetMapping("/view/resume/{userId}")
    public ResponseEntity<ByteArrayResource> viewResume(@PathVariable Long userId) {
        Optional<Resume> resume = resumeRepository.findById(userId);
        if (resume.isPresent()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new ByteArrayResource(resume.get().getResumeData()));
        }
        return ResponseEntity.notFound().build();
    }

    // ** Download Resume **
    @GetMapping("/download/resume/{userId}")
    public ResponseEntity<ByteArrayResource> downloadResume(@PathVariable Long userId) {
        Optional<Resume> resume = resumeRepository.findById(userId);
        if (resume.isPresent()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"resume.pdf\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new ByteArrayResource(resume.get().getResumeData()));
        }
        return ResponseEntity.notFound().build();
    }
}
