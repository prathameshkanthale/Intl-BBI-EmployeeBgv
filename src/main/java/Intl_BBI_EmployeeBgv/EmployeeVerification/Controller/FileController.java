package Intl_BBI_EmployeeBgv.EmployeeVerification.Controller;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.*;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Repository.*;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/files")
public class FileController {

    private final ProfilePhotoRepository profilePhotoRepository;
    private final ResumeRepository resumeRepository;
    private final AadharProofRepository aadharProofRepository;
    private final TenthMarksheetRepository tenthMarksheetRepository;
    private final TwelfthMarksheetRepository twelfthMarksheetRepository;
    private final DiplomaMarksheetRepository diplomaMarksheetRepository;
    private final GraduationMarksheetRepository graduationMarksheetRepository;
    private final PostGraduationMarksheetRepository postGraduationMarksheetRepository;
    private final ExperienceLetterRepository experienceLetterRepository;

    public FileController(ProfilePhotoRepository profilePhotoRepository, 
                          ResumeRepository resumeRepository, 
                          AadharProofRepository aadharProofRepository,
                          TenthMarksheetRepository tenthMarksheetRepository,
                          TwelfthMarksheetRepository twelfthMarksheetRepository,
                          DiplomaMarksheetRepository diplomaMarksheetRepository,
                          GraduationMarksheetRepository graduationMarksheetRepository,
                          PostGraduationMarksheetRepository postGraduationMarksheetRepository,
                          ExperienceLetterRepository experienceLetterRepository) {
        this.profilePhotoRepository = profilePhotoRepository;
        this.resumeRepository = resumeRepository;
        this.aadharProofRepository = aadharProofRepository;
        this.tenthMarksheetRepository = tenthMarksheetRepository;
        this.twelfthMarksheetRepository = twelfthMarksheetRepository;
        this.diplomaMarksheetRepository = diplomaMarksheetRepository;
        this.graduationMarksheetRepository = graduationMarksheetRepository;
        this.postGraduationMarksheetRepository = postGraduationMarksheetRepository;
        this.experienceLetterRepository = experienceLetterRepository;
    }

    // Generic method to handle file response
    private ResponseEntity<ByteArrayResource> handleFileResponse(Optional<?> file, String filename, MediaType mediaType) {
        if (file.isPresent()) {
            byte[] fileData;
            
            if (file.get() instanceof ProfilePhoto) {
                fileData = ((ProfilePhoto) file.get()).getPhotoData();
            } else if (file.get() instanceof Resume) {
                fileData = ((Resume) file.get()).getResumeData();
            } else if (file.get() instanceof AadharProof) {
                fileData = ((AadharProof) file.get()).getAadharData();
            } else if (file.get() instanceof TenthMarksheet) {
                fileData = ((TenthMarksheet) file.get()).getMarksheetData();
            } else if (file.get() instanceof TwelfthMarksheet) {
                fileData = ((TwelfthMarksheet) file.get()).getMarksheetData();
            } else if (file.get() instanceof DiplomaMarksheet) {
                fileData = ((DiplomaMarksheet) file.get()).getMarksheetData();
            } else if (file.get() instanceof GraduationMarksheet) {
                fileData = ((GraduationMarksheet) file.get()).getMarksheetData();
            } else if (file.get() instanceof PostGraduationMarksheet) {
                fileData = ((PostGraduationMarksheet) file.get()).getMarksheetData();
            } else if (file.get() instanceof ExperienceLetter) {
                fileData = ((ExperienceLetter) file.get()).getExperienceLetterData();
            } else {
                return ResponseEntity.badRequest().build();
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .contentType(mediaType)
                    .body(new ByteArrayResource(fileData));
        }
        return ResponseEntity.notFound().build();
    }

    // ** View & Download Profile Photo **
    @GetMapping("/view/profile-photo/{userId}")
    public ResponseEntity<ByteArrayResource> viewProfilePhoto(@PathVariable Long userId) {
        return handleFileResponse(profilePhotoRepository.findByUser_detailId(userId), "profile_photo.jpg", MediaType.IMAGE_JPEG);
    }

    @GetMapping("/download/profile-photo/{userId}")
    public ResponseEntity<ByteArrayResource> downloadProfilePhoto(@PathVariable Long userId) {
        return handleFileResponse(profilePhotoRepository.findByUser_detailId(userId), "profile_photo.jpg", MediaType.IMAGE_JPEG);
    }

    // ** View & Download Resume **
    @GetMapping("/view/resume/{userId}")
    public ResponseEntity<ByteArrayResource> viewResume(@PathVariable Long userId) {
        return handleFileResponse(resumeRepository.findByUser_detailId(userId), "resume.pdf", MediaType.APPLICATION_PDF);
    }

    @GetMapping("/download/resume/{userId}")
    public ResponseEntity<ByteArrayResource> downloadResume(@PathVariable Long userId) {
        return handleFileResponse(resumeRepository.findByUser_detailId(userId), "resume.pdf", MediaType.APPLICATION_PDF);
    }

    // ** View & Download Aadhar Proof **
    @GetMapping("/view/aadhar/{userId}")
    public ResponseEntity<ByteArrayResource> viewAadhar(@PathVariable Long userId) {
        return handleFileResponse(aadharProofRepository.findByUser_detailId(userId), "aadhar.pdf", MediaType.APPLICATION_PDF);
    }

    @GetMapping("/download/aadhar/{userId}")
    public ResponseEntity<ByteArrayResource> downloadAadhar(@PathVariable Long userId) {
        return handleFileResponse(aadharProofRepository.findByUser_detailId(userId), "aadhar.pdf", MediaType.APPLICATION_PDF);
    }

    // ** View & Download 10th Marksheet **
    @GetMapping("/view/tenth-marksheet/{userId}")
    public ResponseEntity<ByteArrayResource> viewTenthMarksheet(@PathVariable Long userId) {
        return handleFileResponse(tenthMarksheetRepository.findByUser_detailId(userId), "10th_marksheet.pdf", MediaType.APPLICATION_PDF);
    }

    @GetMapping("/download/tenth-marksheet/{userId}")
    public ResponseEntity<ByteArrayResource> downloadTenthMarksheet(@PathVariable Long userId) {
        return handleFileResponse(tenthMarksheetRepository.findByUser_detailId(userId), "10th_marksheet.pdf", MediaType.APPLICATION_PDF);
    }

    // ** View & Download 12th Marksheet **
    @GetMapping("/view/twelfth-marksheet/{userId}")
    public ResponseEntity<ByteArrayResource> viewTwelfthMarksheet(@PathVariable Long userId) {
        return handleFileResponse(twelfthMarksheetRepository.findByUser_detailId(userId), "12th_marksheet.pdf", MediaType.APPLICATION_PDF);
    }

    @GetMapping("/download/twelfth-marksheet/{userId}")
    public ResponseEntity<ByteArrayResource> downloadTwelfthMarksheet(@PathVariable Long userId) {
        return handleFileResponse(twelfthMarksheetRepository.findByUser_detailId(userId), "12th_marksheet.pdf", MediaType.APPLICATION_PDF);
    }

    // ** View & Download Diploma Marksheet **
    @GetMapping("/view/diploma-marksheet/{userId}")
    public ResponseEntity<ByteArrayResource> viewDiplomaMarksheet(@PathVariable Long userId) {
        return handleFileResponse(diplomaMarksheetRepository.findByUser_detailId(userId), "diploma_marksheet.pdf", MediaType.APPLICATION_PDF);
    }

    @GetMapping("/download/diploma-marksheet/{userId}")
    public ResponseEntity<ByteArrayResource> downloadDiplomaMarksheet(@PathVariable Long userId) {
        return handleFileResponse(diplomaMarksheetRepository.findByUser_detailId(userId), "diploma_marksheet.pdf", MediaType.APPLICATION_PDF);
    }
    
 // ** View & Download Graduation Marksheet **
    @GetMapping("/view/graduation-marksheet/{userId}")
    public ResponseEntity<ByteArrayResource> viewGraduationMarksheet(@PathVariable Long userId) {
        return handleFileResponse(graduationMarksheetRepository.findByUser_detailId(userId), "graduation_marksheet.pdf", MediaType.APPLICATION_PDF);
    }

    @GetMapping("/download/graduation-marksheet/{userId}")
    public ResponseEntity<ByteArrayResource> downloadGraduationMarksheet(@PathVariable Long userId) {
        return handleFileResponse(graduationMarksheetRepository.findByUser_detailId(userId), "graduation_marksheet.pdf", MediaType.APPLICATION_PDF);
    }

    // ** View & Download PostGraduation Marksheet **
    @GetMapping("/view/postgraduation-marksheet/{userId}")
    public ResponseEntity<ByteArrayResource> viewPostGraduationMarksheet(@PathVariable Long userId) {
        return handleFileResponse(postGraduationMarksheetRepository.findByUser_detailId(userId), "postgraduation_marksheet.pdf", MediaType.APPLICATION_PDF);
    }

    @GetMapping("/download/postgraduation-marksheet/{userId}")
    public ResponseEntity<ByteArrayResource> downloadPostGraduationMarksheet(@PathVariable Long userId) {
        return handleFileResponse(postGraduationMarksheetRepository.findByUser_detailId(userId), "postgraduation_marksheet.pdf", MediaType.APPLICATION_PDF);
    }

    // ** View & Download Experience Letter **
    @GetMapping("/view/experience-letter/{userId}")
    public ResponseEntity<ByteArrayResource> viewExperienceLetter(@PathVariable Long userId) {
        return handleFileResponse(experienceLetterRepository.findByUser_detailId(userId), "experience_letter.pdf", MediaType.APPLICATION_PDF);
    }

    @GetMapping("/download/experience-letter/{userId}")
    public ResponseEntity<ByteArrayResource> downloadExperienceLetter(@PathVariable Long userId) {
        return handleFileResponse(experienceLetterRepository.findByUser_detailId(userId), "experience_letter.pdf", MediaType.APPLICATION_PDF);
    }
}