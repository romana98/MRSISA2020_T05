import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';

import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { Router, Routes, RouterModule} from "@angular/router";
import { FormsModule,ReactiveFormsModule } from "@angular/forms";
import { AddDoctorFormComponent } from './add-doctor-form/add-doctor-form.component';
import { AddClinicCenterAdminFromComponent } from './add-clinic-center-administrator-form/add-clinic-center-administrator-form.component';
import { AddClinicFromComponent } from './add-clinic-form/add-clinic-form.component';
import { AddClinicAdminFromComponent } from './add-clinic-administrator-form/add-clinic-administrator-form.component';
import { EditPatientProfile } from './edit-patient/edit-patient.component';
import { RegisterPatientForm } from './register-patient-form/register-patient.component';
import { AddHallFormComponent } from './add-hall-form/add-hall-form.component';
import { HttpClientModule } from "@angular/common/http"
import {AddMedicineFromComponent} from "./add-medicine-form/add-medicine-form.component";
import {AddDiagnosisFormComponent} from "./add-diagnosis-form/add-diagnosis-form.component";

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
    path : 'addHall',
    component : AddHallFormComponent
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
    EditPatientProfile,
    RegisterPatientForm,
    AddHallFormComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
