import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';

import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { Router, Routes, RouterModule} from "@angular/router";
import { FormsModule,ReactiveFormsModule } from "@angular/forms";
import { AddDoctorFormComponent } from './add-doctor-form/add-doctor-form.component';
import { AddAdminClinicFromComponent } from './add-admin-clinic-form/add-admin-clinic-form.component';
import { HttpClientModule } from "@angular/common/http"

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
    path : '**',
    component : NavigationComponent
  }
]

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    AddDoctorFormComponent,
    AddAdminClinicFromComponent,
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
