import { Component, OnInit } from '@angular/core';
import {clinicAdminModel} from "../edit-clinic-administrator/edit-clinic-administrator.component";
import {MatSnackBar} from "@angular/material/snack-bar";
import {HttpClient, HttpParams} from "@angular/common/http";
import {icon, latLng, LayerGroup, Marker, marker, tileLayer} from 'leaflet';

@Component({
  selector: 'app-edit-clinic',
  templateUrl: './edit-clinic.component.html',
  styleUrls: ['./edit-clinic.component.css']
})
export class EditClinicComponent implements OnInit {

  model: clinicModel = {
    name: '',
    address: '',
    description: '',
    api: null,
  }

  options = {
    layers: [
      tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', { maxZoom: 18, attribution: '...' })
    ],
    zoom: 1,
    center: latLng(0, 0)
  };

  info:any;
  private place: Marker;

  constructor(private _snackBar: MatSnackBar, private http: HttpClient) { }

  async ngOnInit() {

    // @ts-ignore
    this.model = await this.http.get("http://localhost:8081/clinicAdministrator/getClinic").toPromise();


    // @ts-ignore
    const opencage = require('opencage-api-client');

    opencage.geocode({q: this.model.address, key: 'df145e8c933d49e399e5d6703a1e88b1'}).then(data => {
      if (data.status.code == 200) {
        if (data.results.length > 0) {
          var p = data.results[0];
          this.place = marker([p.geometry.lat, p.geometry.lng ], {
            icon: icon({
              iconSize: [ 25, 41 ],
              iconAnchor: [ 13, 41 ],
              iconUrl: 'leaflet/marker-icon.png',
              iconRetinaUrl: 'leaflet/marker-icon-2x.png',
              shadowUrl: 'leaflet/marker-shadow.png'
            })
          }).addTo(this.items);
        }
      } else if (data.status.code == 402) {
        console.log('hit free-trial daily limit');
        console.log('become a customer: https://opencagedata.com/pricing');
      } else {
        // other possible response codes:
        // https://opencagedata.com/api#codes
        console.log('error', data.status.message);
      }
    }).catch(error => {
      console.log('error', error.message);
    });
  }

  items: LayerGroup = new LayerGroup();

  layerMainGroup: LayerGroup[] = [
    this.items
  ];


  EditClinic() {
    let url = "http://localhost:8081/clinic/editClinic"
    this.http.post(url, this.model).subscribe(
      res => {
        this._snackBar.open("Clinic edited successfully!", "Close", {
          duration: 2000,
        });

        this.items.clearLayers();
        // @ts-ignore
        const opencage = require('opencage-api-client');

        opencage.geocode({q: this.model.address, key: 'df145e8c933d49e399e5d6703a1e88b1'}).then(data => {
          if (data.status.code == 200) {
            if (data.results.length > 0) {
              var p = data.results[0];
              this.place = marker([p.geometry.lat, p.geometry.lng ], {
                icon: icon({
                  iconSize: [ 25, 41 ],
                  iconAnchor: [ 13, 41 ],
                  iconUrl: 'leaflet/marker-icon.png',
                  iconRetinaUrl: 'leaflet/marker-icon-2x.png',
                  shadowUrl: 'leaflet/marker-shadow.png'
                })
              }).addTo(this.items);
            }
          } else if (data.status.code == 402) {
            console.log('hit free-trial daily limit');
            console.log('become a customer: https://opencagedata.com/pricing');
          } else {
            // other possible response codes:
            // https://opencagedata.com/api#codes
            console.log('error', data.status.message);
          }
        }).catch(error => {
          console.log('error', error.message);
        });

      },
      err => {
          this._snackBar.open("Error has occurred while editing clinic!", "Close", {
            duration: 2000,
          });

          console.log(err);
      }
    );
  }
}
export interface clinicModel
{
  name: string;
  address: string | RegExp;
  description: string | RegExp;
  api: object;
}
