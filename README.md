# Donation Tracking System

This project is a **JavaFX**-based Donation Tracking System application designed to streamline the management of donations and donors.

## Project Summary
The Donation Tracking System provides a user-friendly interface to manage donation amounts, relationships with donors, and the overall flow of donations.

## Technologies Used
- **Java**: The primary programming language for the application
- **JavaFX**: For user interface design
- **Scene Builder**: For JavaFX FXML design
- **SQLite** (or another database): For storing donation data

## Features
- Add, delete, and update donors
- Record and track donations
- Generate reports showing the total donation amount for each donor
- Simple and intuitive interface

## Setup

### Prerequisites
- Java Development Kit (JDK 11 or later)
- JavaFX SDK
- IDE: IntelliJ IDEA, Eclipse, or NetBeans
- Scene Builder (optional but recommended for UI design)

### Steps
1. Download and include **JavaFX SDK** in your project.
2. Add the necessary dependencies to your project directory.
3. Open the project in your IDE and run it using the following command:

   ```bash
   java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -jar DonationTrackingSystem.jar
   ```

4. Execute the required SQL scripts to initialize the database (e.g., use the `database/init.sql` file).

## Usage
1. When you launch the program, the main screen will feature **Add Donor** and **Donation Records** sections.
2. To add new donors, fill out the relevant form and save it.
3. As donations are recorded, you can view updated data from the database.
4. Access the reporting section to see total donations and donor details.

## Future Enhancements
- More detailed reporting system
- Cloud-based database integration
- Email notification system
- Graphical analyses (chart support)

## Contribution
To contribute to this project, please create a `fork` and submit your changes via a `pull request`.

## License
This project is licensed under the MIT License.

---

**Project Developer**: Koray Mullaoglu / Furkan Cevik / Emine Yigit 

- [coderemine](https://github.com/coderemine) 
- [FurBlood344324](https://github.com/FurBlood344324)
