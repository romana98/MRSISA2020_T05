import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule, ErrorStateMatcher} from '@angular/material/core';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatInputModule} from '@angular/material/input';
import {MatError, MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule, MatButton} from '@angular/material/button';
import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { Routes, RouterModule} from "@angular/router";
import {FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import { AddDoctorFormComponent } from './add-doctor-form/add-doctor-form.component';
import { AddClinicCenterAdminFromComponent } from './add-clinic-center-administrator-form/add-clinic-center-administrator-form.component';
import { AddClinicFromComponent } from './add-clinic-form/add-clinic-form.component';
import { AddClinicAdminFromComponent } from './add-clinic-administrator-form/add-clinic-administrator-form.component';
import { EditPatientProfile } from './edit-patient/edit-patient.component';
import { RegisterPatientForm } from './register-patient-form/register-patient.component';
import { AddHallFormComponent } from './add-hall-form/add-hall-form.component';
import { EditMedicalStaff } from './edit-medical-staff/edit-medical-staff.component';
import { HttpClientModule } from "@angular/common/http"
import {AddMedicineFromComponent} from "./add-medicine-form/add-medicine-form.component";
import {AddDiagnosisFormComponent} from "./add-diagnosis-form/add-diagnosis-form.component";
import {DialogOverview, RequestListPatientsComponent} from "./request-list-patients/request-list-patients.component";
import { AddPredifinedAppointmentComponent } from './add-predifined-appointment/add-predifined-appointment.component';
import { AddAppointmentTypeComponent } from './add-appointment-type/add-appointment-type.component';
import {MatIconModule} from "@angular/material/icon";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSnackBar, MatSnackBarModule} from "@angular/material/snack-bar";
import {MatDialogModule} from "@angular/material/dialog";
import {ViewPatientsNurseComponent} from "./view-patients-nurse/view-patients-nurse.component";

const appRoutes : Routes = [
  {
    path : 'addDoctor',
    component : AddDoctorFormComponent
  },
  {
    path : '',
    component : AddDoctorFormComponent,
    pathMatch : 'full'

  },
  {
    path: 'addClinicCenterAdministrator',
    component : AddClinicCenterAdminFromComponent
  },
  {
    path: 'addClinic',
    component : AddClinicFromComponent
  },
  {
    path: 'addClinicAdministrator',
    component : AddClinicAdminFromComponent
  },
  {
    path: 'editPatient',
    component : EditPatientProfile
  },
  {
    path : 'registerPatient',
    component : RegisterPatientForm
  },

  {
    path : 'addPredefinedAppointment',
    component : AddPredifinedAppointmentComponent
  },

  {
    path : 'addAppointmentType',
    component: AddAppointmentTypeComponent
  },

  {
    path : 'addHall',
    component : AddHallFormComponent
  },
  {
    path: 'editMedicalStaff',
    component : EditMedicalStaff
  },
  {
    path : 'addMedicine',
    component : AddMedicineFromComponent
  },
  {
    path : 'addDiagnosis',
    component : AddDiagnosisFormComponent
  },
  {
    path : 'requestTable',
    component : RequestListPatientsComponent
  },
  {
    path : 'viewPatients',
    component : ViewPatientsNurseComponent
  },
  {
    path : '**',
    component : NavigationComponent
  }


]

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    AddDoctorFormComponent,
    AddClinicCenterAdminFromComponent,
    AddClinicFromComponent,
    AddClinicAdminFromComponent,
    AddMedicineFromComponent,
    AddDiagnosisFormComponent,
    RequestListPatientsComponent,
    EditPatientProfile,
    RegisterPatientForm,
    AddPredifinedAppointmentComponent,
    AddHallFormComponent,
    EditMedicalStaff,
    AddAppointmentTypeComponent,
    DialogOverview,
    ViewPatientsNurseComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    BrowserAnimationsModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    NgxMaterialTimepickerModule,
    MatSelectModule,
    MatDividerModule,
    MatButtonModule,
    MatIconModule,
    MatTableModule,
    MatPaginatorModule,
    MatSnackBarModule,
    MatDialogModule,
    FormsModule


  ],
  providers: [MatDatepickerModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
