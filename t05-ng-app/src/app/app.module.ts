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
import { AddHallFormComponent } from './add-hall-form/add-hall-form.component';
import { EditMedicalStaff } from './edit-medical-staff/edit-medical-staff.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http"
import {AddMedicineFromComponent} from "./add-medicine-form/add-medicine-form.component";
import {AddDiagnosisFormComponent} from "./add-diagnosis-form/add-diagnosis-form.component";
import {DialogOverview, RequestListPatientsComponent} from "./request-list-patients/request-list-patients.component";
import { AddPredifinedAppointmentComponent } from './add-predifined-appointment/add-predifined-appointment.component';
import { AddAppointmentTypeComponent } from './add-appointment-type/add-appointment-type.component';
import {AddNurseFormComponent} from "./add-nurse-form/add-nurse-form.component";
import {ViewClinicsComponent} from "./view-clinics/view-clinics.component";
import {MatIconModule} from "@angular/material/icon";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatDialogModule} from "@angular/material/dialog";
import {ViewPatientsNurseComponent} from "./view-patients-nurse/view-patients-nurse.component";
import {MatSortModule} from "@angular/material/sort";
import {EditClinicAdministratorComponent} from "./edit-clinic-administrator/edit-clinic-administrator.component";
import { ClinicsComponent } from './patient/clinics/clinics.component';
import {MatExpansionModule, matExpansionAnimations} from '@angular/material/expansion';
import { InterceptorService } from './services/interceptor.service';
import { PatientGuardService } from './guards/patient-guard.service';
import { LoginComponent } from './login/login.component';
import {MatTabsModule} from '@angular/material/tabs';
import { ClinicAdminGuardService } from './guards/clinic-admin-guard.service';
import { LoginGuardService } from './guards/login-guard.service';
import { ClinicCenterAdminGuardService } from './guards/clinic-center-admin-guard.service';
import { DoctorGuardService } from './guards/doctor-guard.service';
import { NurseGuardService } from './guards/nurse-guard.service';
import { MedicallStuffGuardService } from './guards/medicall-stuff-guard.service';





const appRoutes : Routes = [
  {
    path : 'addDoctor',
    component : AddDoctorFormComponent,
    canActivate : [ClinicAdminGuardService]
  },
  {
    path : '',
    component : LoginComponent,
    pathMatch : 'full',
    canActivate : [LoginGuardService]

  },

  {
    path: 'clinicCenterAdmin/addClinicCenterAdministrator',
    component : AddClinicCenterAdminFromComponent,
    canActivate : [ClinicCenterAdminGuardService]
  },
  {
    path: 'clinicCenterAdmin/addClinic',
    component : AddClinicFromComponent,
    canActivate : [ClinicCenterAdminGuardService]
  },
  {
    path: 'clinicCenterAdmin/addClinicAdministrator',
    component : AddClinicAdminFromComponent,
    canActivate : [ClinicCenterAdminGuardService]
  },
  {
    path: 'editPatient',
    component : EditPatientProfile,
    canActivate : [PatientGuardService]
  },

  {
    path : 'addPredefinedAppointment',
    component : AddPredifinedAppointmentComponent,
    canActivate : [ClinicAdminGuardService]
  },

  {
    path : 'addAppointmentType',
    component: AddAppointmentTypeComponent,
    canActivate : [ClinicAdminGuardService]
  },

  {
    path : 'addHall',
    component : AddHallFormComponent,
    canActivate : [ClinicAdminGuardService]
  },
  {
    path: 'editMedicalStaff',
    component : EditMedicalStaff,
    canActivate : [MedicallStuffGuardService]
  },
  {
    path: 'editProfileCA',
    component : EditClinicAdministratorComponent,
    canActivate : [ClinicAdminGuardService]
  },
  {
    path : 'clinicCenterAdmin/addMedicine',
    component : AddMedicineFromComponent,
    canActivate : [ClinicCenterAdminGuardService]
  },
  {
    path : 'clinicCenterAdmin/addDiagnosis',
    component : AddDiagnosisFormComponent,
    canActivate : [ClinicCenterAdminGuardService]
  },
  {
    path : 'clinicCenterAdmin/requestTable',
    component : RequestListPatientsComponent,
    canActivate : [ClinicCenterAdminGuardService]
  },
  {
    path : 'addNurse',
    component : AddNurseFormComponent,
    canActivate : [ClinicAdminGuardService]
  },
  {
    path: 'viewClinics',
    component: ViewClinicsComponent,
    canActivate : [ClinicCenterAdminGuardService]
  },
  {
    path : 'viewPatients',
    component : ViewPatientsNurseComponent,
    canActivate : [MedicallStuffGuardService]
  },
  {
    path: 'patient/clinics',
    component: ClinicsComponent,
    canActivate : [PatientGuardService]
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
    AddPredifinedAppointmentComponent,
    AddHallFormComponent,
    EditMedicalStaff,
    AddAppointmentTypeComponent,
    AddNurseFormComponent,
    ViewClinicsComponent,
    ViewPatientsNurseComponent,
    DialogOverview,
    EditClinicAdministratorComponent,
    ClinicsComponent,
    LoginComponent
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
    FormsModule,
    MatSortModule,
    MatExpansionModule,
    MatTabsModule

  ],
  providers: [MatDatepickerModule,
    {
      provide: HTTP_INTERCEPTORS, useClass:InterceptorService, multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
