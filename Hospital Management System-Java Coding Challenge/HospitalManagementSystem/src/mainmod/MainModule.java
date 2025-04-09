/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainmod;

/**
 *
 * @author DELL
 */


import dao.*;
import entity.*;
import myexceptions.PatientNumberNotFoundException;

import java.util.List;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        IPatientService patientService = new PatientServiceImpl();
        IDoctorService doctorService = new DoctorServiceImpl();
        IHospitalService appointmentService = new HospitalServiceImpl();

        int choice;

        do {
            System.out.println("\n=== Hospital Management System ===");
            System.out.println("1. Add Patient");
            System.out.println("2. Update Patient");
            System.out.println("3. Delete Patient");
            System.out.println("4. View All Patients");
            System.out.println("5. Add Doctor");
            System.out.println("6. Update Doctor");
            System.out.println("7. Delete Doctor");
            System.out.println("8. View All Doctors");
            System.out.println("9. View Appointment by ID");
            System.out.println("10. View Appointments by Patient ID");
            System.out.println("11. View Appointments by Doctor ID");
            System.out.println("12. Schedule Appointment");
            System.out.println("13. Update Appointment");
            System.out.println("14. Cancel Appointment");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Patient ID: ");
                        int pid = sc.nextInt(); sc.nextLine();
                        System.out.print("First Name: ");
                        String pfname = sc.nextLine();
                        System.out.print("Last Name: ");
                        String plname = sc.nextLine();
                        System.out.print("DOB (YYYY-MM-DD): ");
                        String dob = sc.nextLine();
                        System.out.print("Gender: ");
                        String gender = sc.nextLine();
                        System.out.print("Contact Number: ");
                        String contact = sc.nextLine();
                        System.out.print("Address: ");
                        String address = sc.nextLine();
                        Patient patient = new Patient(pid, pfname, plname, dob, gender, contact, address);
                        System.out.println(patientService.addPatient(patient) ? " Patient added." : " Failed.");
                        break;

                    case 2:
                        System.out.print("Patient ID to update: ");
                        pid = sc.nextInt(); sc.nextLine();
                        System.out.print("First Name: ");
                        pfname = sc.nextLine();
                        System.out.print("Last Name: ");
                        plname = sc.nextLine();
                        System.out.print("DOB: ");
                        dob = sc.nextLine();
                        System.out.print("Gender: ");
                        gender = sc.nextLine();
                        System.out.print("Contact Number: ");
                        contact = sc.nextLine();
                        System.out.print("Address: ");
                        address = sc.nextLine();
                        Patient updatedPatient = new Patient(pid, pfname, plname, dob, gender, contact, address);
                        System.out.println(patientService.updatePatient(updatedPatient) ? " Updated." : " Failed.");
                        break;

                    case 3:
                        System.out.print("Patient ID to delete: ");
                        System.out.println(patientService.deletePatient(sc.nextInt()) ? " Deleted." : " Failed.");
                        break;

                    case 4:
                        List<Patient> patients = patientService.getAllPatients();
                        for (Patient pat : patients) {
                            pat.printDetails(); 
                        }
                        break;

                    case 5:
                        System.out.print("Doctor ID: ");
                        int did = sc.nextInt(); sc.nextLine();
                        System.out.print("First Name: ");
                        String dfname = sc.nextLine();
                        System.out.print("Last Name: ");
                        String dlname = sc.nextLine();
                        System.out.print("Specialization: ");
                        String spec = sc.nextLine();
                        System.out.print("Contact Number: ");
                        String dcontact = sc.nextLine();
                        Doctor doctor = new Doctor(did, dfname, dlname, spec, dcontact);
                        System.out.println(doctorService.addDoctor(doctor) ? " Doctor added." : " Failed.");
                        break;

                    case 6:
                        System.out.print("Doctor ID to update: ");
                        did = sc.nextInt(); sc.nextLine();
                        System.out.print("First Name: ");
                        dfname = sc.nextLine();
                        System.out.print("Last Name: ");
                        dlname = sc.nextLine();
                        System.out.print("Specialization: ");
                        spec = sc.nextLine();
                        System.out.print("Contact Number: ");
                        dcontact = sc.nextLine();
                        Doctor updatedDoctor = new Doctor(did, dfname, dlname, spec, dcontact);
                        System.out.println(doctorService.updateDoctor(updatedDoctor) ? " Updated." : " Failed.");
                        break;

                    case 7:
                        System.out.print("Doctor ID to delete: ");
                        System.out.println(doctorService.deleteDoctor(sc.nextInt()) ? " Deleted." : " Failed.");
                        break;

                    case 8:
                        List<Doctor> doctors = doctorService.getAllDoctors();
                        for (Doctor d : doctors) {
                            d.printDetails(); 
                        }
                        break;

                    case 9:
                        System.out.print("Enter Appointment ID: ");
                        Appointment appt = appointmentService.getAppointmentById(sc.nextInt());
                        System.out.println(appt != null ? appt.toString() : " Appointment Not Found");
                        break;

                    case 10:
                        System.out.print("Enter Patient ID: ");
                        int patientId = sc.nextInt();
                        List<Appointment> patList = appointmentService.getAppointmentsForPatient(patientId);
                        if (patList.isEmpty()) {
                            System.out.println(" No appointments found for Patient ID: " + patientId);
                        } else {
                            System.out.println(" Appointments for Patient ID " + patientId + ":");
                            for (Appointment a : patList) {
                                a.printDetails(); 
                            }
                        }
                        break;

                    case 11:
                        System.out.print("Enter Doctor ID: ");
                        int doctorId = sc.nextInt();
                        List<Appointment> docList = appointmentService.getAppointmentsForDoctor(doctorId);
                        if (docList.isEmpty()) {
                            System.out.println(" No appointments found for Doctor ID: " + doctorId);
                        } else {
                            for (Appointment a : docList) {
                                a.printDetails();
                            }
                        }
                        break;

                    case 12:
                        System.out.println("Enter Appointment ID, Patient ID, Doctor ID, Date (YYYY-MM-DD), Description:");
                        int aid = sc.nextInt();
                        int pidA = sc.nextInt();
                        int didA = sc.nextInt();
                        sc.nextLine();
                        String date = sc.nextLine();
                        String desc = sc.nextLine();
                        Appointment newAppt = new Appointment(aid, pidA, didA, date, desc);
                        System.out.println(appointmentService.scheduleAppointment(newAppt) ? " Scheduled." : " Failed.");
                        break;

                    case 13:
                        System.out.println("Enter Appointment ID, Patient ID, Doctor ID, Date (YYYY-MM-DD), Description:");
                        int aidU = sc.nextInt();
                        int pidU = sc.nextInt();
                        int didU = sc.nextInt();
                        sc.nextLine();
                        String dateU = sc.nextLine();
                        String descU = sc.nextLine();
                        Appointment updateAppt = new Appointment(aidU, pidU, didU, dateU, descU);
                        System.out.println(appointmentService.updateAppointment(updateAppt) ? " Updated." : " Failed.");
                        break;

                    case 14:
                        System.out.print("Enter Appointment ID to cancel: ");
                        System.out.println(appointmentService.cancelAppointment(sc.nextInt()) ? " Cancelled." : " Not Found.");
                        break;

                    case 0:
                        System.out.println(" Thank you! Exiting system...");
                        break;

                    default:
                        System.out.println(" Invalid option! Try again.");
                }
            } catch (PatientNumberNotFoundException e) {
                System.err.println(" Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println(" Unexpected error occurred.");
                e.printStackTrace();
            }
        } while (choice != 0);

        sc.close();
    }
}
