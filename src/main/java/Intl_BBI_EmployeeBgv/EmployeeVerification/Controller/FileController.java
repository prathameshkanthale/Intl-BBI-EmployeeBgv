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
    @GetMapping("/view/profile-photo/{detailId}")
    public ResponseEntity<ByteArrayResource> viewProfilePhoto(@PathVariable Long detailId) {
        return handleFileResponse(profilePhotoRepository.findByUser_detailId(detailId), "profile_photo.jpg", MediaType.IMAGE_JPEG);
    }

    @GetMapping("/download/profile-photo/{detailId}")
    public ResponseEntity<ByteArrayResource> downloadProfilePhoto(@PathVariable Long detailId) {
        return handleFileResponse(profilePhotoRepository.findByUser_detailId(detailId), "profile_photo.jpg", MediaType.IMAGE_JPEG);
    }

    // ** View & Download Resume **
    @GetMapping("/view/resume/{detailId}")
    public ResponseEntity<ByteArrayResource> viewResume(@PathVariable Long detailId) {
        return handleFileResponse(resumeRepository.findByUser_detailId(detailId), "resume.pdf", MediaType.APPLICATION_PDF);
    }

    @GetMapping("/download/resume/{detailId}")
    public ResponseEntity<ByteArrayResource> downloadResume(@PathVariable Long detailId) {
        return handleFileResponse(resumeRepository.findByUser_detailId(detailId), "resume.pdf", MediaType.APPLICATION_PDF);
    }

    // ** View & Download Aadhar Proof **
    @GetMapping("/view/aadhar/{detailId}")
    public ResponseEntity<ByteArrayResource> viewAadhar(@PathVariable Long detailId) {
        return handleFileResponse(aadharProofRepository.findByUser_detailId(detailId), "aadhar.pdf", MediaType.APPLICATION_PDF);
    }

    @GetMapping("/download/aadhar/{detailId}")
    public ResponseEntity<ByteArrayResource> downloadAadhar(@PathVariable Long detailId) {
        return handleFileResponse(aadharProofRepository.findByUser_detailId(detailId), "aadhar.pdf", MediaType.APPLICATION_PDF);
    }

    // ** View & Download 10th Marksheet **
    @GetMapping("/view/tenth-marksheet/{detailId}")
    public ResponseEntity<ByteArrayResource> viewTenthMarksheet(@PathVariable Long detailId) {
        return handleFileResponse(tenthMarksheetRepository.findByUser_detailId(detailId), "10th_marksheet.pdf", MediaType.APPLICATION_PDF);
    }

    @GetMapping("/download/tenth-marksheet/{detailId}")
    public ResponseEntity<ByteArrayResource> downloadTenthMarksheet(@PathVariable Long detailId) {
        return handleFileResponse(tenthMarksheetRepository.findByUser_detailId(detailId), "10th_marksheet.pdf", MediaType.APPLICATION_PDF);
    }

    // ** View & Download 12th Marksheet **
    @GetMapping("/view/twelfth-marksheet/{detailId}")
    public ResponseEntity<ByteArrayResource> viewTwelfthMarksheet(@PathVariable Long detailId) {
        return handleFileResponse(twelfthMarksheetRepository.findByUser_detailId(detailId), "12th_marksheet.pdf", MediaType.APPLICATION_PDF);
    }

    @GetMapping("/download/twelfth-marksheet/{detailId}")
    public ResponseEntity<ByteArrayResource> downloadTwelfthMarksheet(@PathVariable Long detailId) {
        return handleFileResponse(twelfthMarksheetRepository.findByUser_detailId(detailId), "12th_marksheet.pdf", MediaType.APPLICATION_PDF);
    }

    // ** View & Download Diploma Marksheet **
    @GetMapping("/view/diploma-marksheet/{detailId}")
    public ResponseEntity<ByteArrayResource> viewDiplomaMarksheet(@PathVariable Long detailId) {
        return handleFileResponse(diplomaMarksheetRepository.findByUser_detailId(detailId), "diploma_marksheet.pdf", MediaType.APPLICATION_PDF);
    }

    @GetMapping("/download/diploma-marksheet/{detailId}")
    public ResponseEntity<ByteArrayResource> downloadDiplomaMarksheet(@PathVariable Long detailId) {
        return handleFileResponse(diplomaMarksheetRepository.findByUser_detailId(detailId), "diploma_marksheet.pdf", MediaType.APPLICATION_PDF);
    }
    
 // ** View & Download Graduation Marksheet **
    @GetMapping("/view/graduation-marksheet/{detailId}")
    public ResponseEntity<ByteArrayResource> viewGraduationMarksheet(@PathVariable Long detailId) {
        return handleFileResponse(graduationMarksheetRepository.findByUser_detailId(detailId), "graduation_marksheet.pdf", MediaType.APPLICATION_PDF);
    }

    @GetMapping("/download/graduation-marksheet/{detailId}")
    public ResponseEntity<ByteArrayResource> downloadGraduationMarksheet(@PathVariable Long detailId) {
        return handleFileResponse(graduationMarksheetRepository.findByUser_detailId(detailId), "graduation_marksheet.pdf", MediaType.APPLICATION_PDF);
    }

    // ** View & Download PostGraduation Marksheet **
    @GetMapping("/view/postgraduation-marksheet/{detailId}")
    public ResponseEntity<ByteArrayResource> viewPostGraduationMarksheet(@PathVariable Long detailId) {
        return handleFileResponse(postGraduationMarksheetRepository.findByUser_detailId(detailId), "postgraduation_marksheet.pdf", MediaType.APPLICATION_PDF);
    }

    @GetMapping("/download/postgraduation-marksheet/{detailId}")
    public ResponseEntity<ByteArrayResource> downloadPostGraduationMarksheet(@PathVariable Long detailId) {
        return handleFileResponse(postGraduationMarksheetRepository.findByUser_detailId(detailId), "postgraduation_marksheet.pdf", MediaType.APPLICATION_PDF);
    }

    // ** View & Download Experience Letter **
    @GetMapping("/view/experience-letter/{detailId}")
    public ResponseEntity<ByteArrayResource> viewExperienceLetter(@PathVariable Long detailId) {
        return handleFileResponse(experienceLetterRepository.findByUser_detailId(detailId), "experience_letter.pdf", MediaType.APPLICATION_PDF);
    }

    @GetMapping("/download/experience-letter/{detailId}")
    public ResponseEntity<ByteArrayResource> downloadExperienceLetter(@PathVariable Long detailId) {
        return handleFileResponse(experienceLetterRepository.findByUser_detailId(detailId), "experience_letter.pdf", MediaType.APPLICATION_PDF);
    }
}