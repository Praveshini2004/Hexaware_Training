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

import dao.HospitalServiceImpl;
import entity.Appointment;
import myexceptions.PatientNumberNotFoundException;

import java.util.List;
import java.util.Scanner;

public class MainModule {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        HospitalServiceImpl service = new HospitalServiceImpl();
        int choice;

        do {
            System.out.println("\n===== Hospital Management System Menu =====");
            System.out.println("1. Get Appointment by ID");
            System.out.println("2. Get Appointments for a Patient");
            System.out.println("3. Get Appointments for a Doctor");
            System.out.println("4. Schedule an Appointment");
            System.out.println("5. Update an Appointment");
            System.out.println("6. Cancel an Appointment");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Appointment ID: ");
                    int aid = sc.nextInt();
                    Appointment a = service.getAppointmentById(aid);
                    if (a != null) {
                        a.displayInfo();
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Patient ID: ");
                    int pid = sc.nextInt();
                    try {
                        List<Appointment> plist = service.getAppointmentsForPatient(pid);
                        for (Appointment pa : plist) {
                            pa.displayInfo();
                            System.out.println("----------------------------------");
                        }
                    } catch (PatientNumberNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Enter Doctor ID: ");
                    int did = sc.nextInt();
                    List<Appointment> dlist = service.getAppointmentsForDoctor(did);
                    if (dlist.isEmpty()) {
                        System.out.println("No appointments found for doctor.");
                    } else {
                        for (Appointment da : dlist) {
                            da.displayInfo();
                            System.out.println("----------------------------------");
                        }
                    }
                    break;

                case 4:
                    System.out.println("Enter details for new appointment:");
                    System.out.print("Appointment ID: ");
                    int newAid = sc.nextInt();
                    System.out.print("Patient ID: ");
                    int newPid = sc.nextInt();
                    System.out.print("Doctor ID: ");
                    int newDid = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Appointment Date (yyyy-mm-dd): ");
                    String date = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();

                    Appointment newAppt = new Appointment(newAid, newPid, newDid, date, desc);
                    boolean scheduled = service.scheduleAppointment(newAppt);
                    System.out.println(scheduled ? "Appointment scheduled successfully." : "Failed to schedule appointment.");
                    break;

                case 5:
                    System.out.println("Enter details to update appointment:");
                    System.out.print("Appointment ID: ");
                    int upAid = sc.nextInt();
                    System.out.print("New Patient ID: ");
                    int upPid = sc.nextInt();
                    System.out.print("New Doctor ID: ");
                    int upDid = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("New Appointment Date (yyyy-mm-dd): ");
                    String upDate = sc.nextLine();
                    System.out.print("New Description: ");
                    String upDesc = sc.nextLine();

                    Appointment updatedAppt = new Appointment(upAid, upPid, upDid, upDate, upDesc);
                    boolean updated = service.updateAppointment(updatedAppt);
                    System.out.println(updated ? "Appointment updated successfully." : "Failed to update appointment.");
                    break;

                case 6:
                    System.out.print("Enter Appointment ID to cancel: ");
                    int delAid = sc.nextInt();
                    boolean deleted = service.cancelAppointment(delAid);
                    System.out.println(deleted ? "Appointment canceled successfully." : "Failed to cancel appointment.");
                    break;

                case 7:
                    System.out.println("Exiting the application...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 7);

        sc.close();
    }
}
