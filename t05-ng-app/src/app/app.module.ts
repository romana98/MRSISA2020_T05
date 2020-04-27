import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';

import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { Router, Routes, RouterModule} from "@angular/router";
import { FormsModule,ReactiveFormsModule } from "@angular/forms";
import { AddDoctorFormComponent } from './add-doctor-form/add-doctor-form.component';
import { AddAdminClinicFromComponent } from './add-admin-clinic-form/add-admin-clinic-form.component';
import { AddClinicFromComponent } from './add-clinic-form/add-clinic-form.component';
import { AddAdminFromComponent } from './add-admin-form/add-admin-form.component';
import { EditPatientProfile } from './edit-patient/edit-patient.component';
import { RegisterPatientForm } from './register-patient-form/register-patient.component';
import { HttpClientModule } from "@angular/common/http";
import { AddPredifinedAppointmentComponent } from './add-predifined-appointment/add-predifined-appointment.component'

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
    path: 'addAdminClinic',
    component : AddAdminClinicFromComponent
  },
  {
    path: 'addClinic',
    component : AddClinicFromComponent
  },
  {
    path: 'addAdmin',
    component : AddAdminFromComponent
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
  }

]

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    AddDoctorFormComponent,
    AddAdminClinicFromComponent,
    AddClinicFromComponent,
    AddAdminFromComponent,
    EditPatientProfile,
    RegisterPatientForm,
    AddPredifinedAppointmentComponent,
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
