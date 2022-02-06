package com.example.droneAPIservice.controllerpackage;

import com.example.droneAPIservice.datatransferpackage.MedicineDataTransfer;
import com.example.droneAPIservice.entitypackage.Medication;
import com.example.droneAPIservice.services.ImageService;
import com.example.droneAPIservice.services.MedicationService;
import com.example.droneAPIservice.utilities.ResourceNotFoundException;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping(value = "/med", produces = "application/json", consumes = "application/json")
public class MedicationController {
    @Autowired
    private MedicationService mService;

    @Autowired
    private ImageService imageService;

    @PostMapping()
    public ResponseEntity<Medication> register(@NotNull @Valid @RequestBody MedicineDataTransfer dto)
            throws IllegalArgumentException {
        Medication med = this.mService.save(dto);
        return ResponseEntity.ok(med);
    }

    @PostMapping(value = "upload/{id}", consumes = { "multipart/form-data" })
    public ResponseEntity<Medication> imageUpload(@PathVariable Long id,
                                                  @RequestParam("image") Optional<MultipartFile> imageFile)
            throws ResourceNotFoundException, IOException {
        byte[] imageData = this.imageService.processImage(imageFile.get());
        return ResponseEntity.ok(this.mService.imageUpdate(id, imageData));
    }

    @GetMapping("{id}")
    public ResponseEntity<Medication> findById(@PathVariable Long id)
            throws ResourceNotFoundException, IOException, DataFormatException {
        Medication med = this.mService.findById(id);
        if (med.getImage() != null) {
            byte[] imageData = this.imageService.decompress(med.getImage());
            med.setImage(imageData);
        }
        return ResponseEntity.ok(med);
    }

    @PutMapping()
    public ResponseEntity<Medication> update(@NotNull @Valid @RequestBody MedicineDataTransfer dto)
            throws ResourceNotFoundException, IllegalArgumentException {
        return ResponseEntity.ok(this.mService.update(dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) throws ResourceNotFoundException, IllegalArgumentException {
        this.mService.delete(id);
        return ResponseEntity.ok("");
    }

}
