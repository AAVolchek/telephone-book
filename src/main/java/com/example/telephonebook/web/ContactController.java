package com.example.telephonebook.web;

import com.example.telephonebook.model.Contact;
import com.example.telephonebook.service.ContactService;
import com.example.telephonebook.util.SecurityUserUtil;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/contacts")
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public List<Contact> getAllContacts() {
        Long userId = SecurityUserUtil.authUserId();
        return contactService.getAllContacts(userId);
    }

    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable Long id) {
        Long userId = SecurityUserUtil.authUserId();
        return contactService.getContactById(id, userId);
    }

   @PostMapping
    public void addContact(@RequestBody Contact contact) {
        Long userId = SecurityUserUtil.authUserId();
        contactService.saveContact(contact, userId);
    }

    @PutMapping("/{id}")
    public void updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        Long userId = SecurityUserUtil.authUserId();
        Contact existingContact = contactService.getContactById(id, userId);
        if (existingContact != null) {
            contact.setId(id);
            contactService.saveContact(contact, userId);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        Long userId = SecurityUserUtil.authUserId();
        contactService.deleteContact(id, userId);
    }

    @GetMapping("/export")
    public void exportContacts(HttpServletResponse response) throws IOException {
        Long userId = SecurityUserUtil.authUserId();
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"contacts.csv\"");
        List<Contact> contacts = contactService.getAllContacts(userId);

        CSVWriter  csvWriter = new CSVWriter(response.getWriter(), CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        String[] header = {"First Name", "Last name", "Phone Number", "Birthday", "Social Profile", "Email", "Group"};

        csvWriter.writeNext(header);
        for (Contact contact : contacts) {
            String[] data = {contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber(),
                    String.valueOf(contact.getBirthday()), contact.getSocialProfile(), contact.getEmail(), contact.getGroup()};
            csvWriter.writeNext(data);
        }
        csvWriter.close();
    }

    @PostMapping("/import")
    public ResponseEntity<String> importContacts(@RequestParam("file") MultipartFile file) {
        try {
            Long userId = SecurityUserUtil.authUserId();
            Reader reader = new InputStreamReader(file.getInputStream());
            List<String[]> rows = new CSVReaderBuilder(reader).withSkipLines(1).build().readAll();
            for (String[] row : rows) {
                if (row.length != 7) {
                    return ResponseEntity.badRequest().body("Invalid row: " + Arrays.toString(row));
                }
                Contact contact = new Contact();
                contact.setFirstName(row[0]);
                contact.setLastName(row[1]);
                contact.setPhoneNumber(row[2]);
                if (!"null".equals(row[3])) {
                    try {
                        contact.setBirthday(LocalDate.parse(row[3]));
                    } catch (DateTimeParseException e) {
                        return ResponseEntity.badRequest().body("Invalid date format in row: " + Arrays.toString(row));
                    }
                }
                contact.setSocialProfile(row[4]);
                contact.setEmail(row[5]);
                contact.setGroup(row[6]);
                contactService.saveContact(contact, userId);
            }
            return ResponseEntity.ok().build();
        } catch (CsvException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file: " + e.getMessage());
        }
    }
}
