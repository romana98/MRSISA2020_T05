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
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatMenuModule} from '@angular/material/menu';
import {RequiredPassDirective} from "./directive/RequiredPassDirective";





const appRoutes : Routes = [
  {
    path: 'clinicAdmin',
    canActivate: [ClinicAdminGuardService],
    children: [
      {
        path : 'addDoctor',
        component : AddDoctorFormComponent
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
        path: 'editProfile',
        component : EditClinicAdministratorComponent
      },
      {
        path : 'addNurse',
        component : AddNurseFormComponent
      },
    ]
  },
  {
    path: 'clinicCenterAdmin',
    canActivate: [ClinicCenterAdminGuardService],
    children: [
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
    ]
  },
  
  {
    path:'patient',
    canActivate: [PatientGuardService],
    children:[
      {
        path: 'editPatient',
        component : EditPatientProfile
      },
      {
        path: 'clinics',
        component: ClinicsComponent
      },
    ]
  },
  {
    path : '',
    component : LoginComponent,
    pathMatch : 'full',
    canActivate : [LoginGuardService]

  },
 
  {
    path: 'editMedicalStaff',
    component : EditMedicalStaff,
    canActivate : [MedicallStuffGuardService]
  },
 
  {
    path : 'viewPatients',
    component : ViewPatientsNurseComponent,
    canActivate : [MedicallStuffGuardService]
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
    LoginComponent,
    RequiredPassDirective
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
    MatTabsModule,
    MatToolbarModule,
    MatMenuModule

  ],
  providers: [MatDatepickerModule,
    {
      provide: HTTP_INTERCEPTORS, useClass:InterceptorService, multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
