package com.amagana.patient_mvc.controllers;

import com.amagana.patient_mvc.dto.PatientDtoRequest;
import com.amagana.patient_mvc.dto.PatientDtoResponse;
import com.amagana.patient_mvc.entities.Patient;
import com.amagana.patient_mvc.service.PatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// import java.util.List;

@Controller
@AllArgsConstructor
//@RequestMapping("api/v1/patient")
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/patient")
    public String getAllPatients(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "5") int size,
                                 @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<PatientDtoResponse> patients = patientService.getPatientPage(keyword, page, size);
        System.out.println(patients);
        model.addAttribute("listPatients", patients.getContent());
        model.addAttribute("pages", new int[patients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping("/admin/delete")
    public String deletePatient(Long id, String keyword, int page) {
        patientService.deletePatient(id);
        return "redirect:/patient?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    /*@GetMapping("/patients")
    @ResponseBody
    public List<Patient> listPatients() {
        return patientRepository.findAll();
    }*/

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/formPatient")
    public String formPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "patientForm";
    }
    @PostMapping("/save")
    public String createPatient(Model model, @Valid PatientDtoRequest patientDtoRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
                return "patientForm";
        patientService.createPatient(patientDtoRequest);
        return "redirect:/admin/formPatient";
    }

    @PostMapping("/update")
    public String updatePatient(Model model, @Valid PatientDtoRequest patientDtoRequest, Long id, BindingResult bindingResult,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        if (bindingResult.hasErrors())
            return "patientForm";
        patientService.updatePatient(patientDtoRequest, id);
        return "redirect:/patient?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/edit")
    public String editPatient(Model model, Long id, int page, String keyword) {
        PatientDtoResponse patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editForm";
    }
}
