import {Component, OnInit, Inject, ViewChild} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import { FormControl, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import {MatSort} from "@angular/material/sort";
import {AppointmentTypeModel} from "../add-appointment-type/add-appointment-type.component";
import {MatExpansionPanel} from "@angular/material/expansion";

@Component({
  selector: 'app-add-pricelist',
  templateUrl: './add-pricelist.component.html',
  styleUrls: ['./add-pricelist.component.css']
})
export class AddPricelistComponent implements OnInit {

  model : priceListModel = {
    id: null,
    apt_type: '',
    price: null
  }

  currentlySelected : priceListModel = {
    id: null,
    apt_type: '',
    price: null
  }

  displayedColumns = ['apt_type', 'price', 'delete'];

  apt_types:any = [];

  dataSource = new MatTableDataSource();
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild('expanel') expanel: MatExpansionPanel;

  isDisabled: boolean = true;

  constructor(private _snackBar: MatSnackBar,private http: HttpClient) {
  }

  ngOnInit(): void {
    this.http.get("https://eclinic05.herokuapp.com/pricelist/getAptTypes").subscribe(
      res => {
        this.apt_types = res;
      });
    this.http.get("https://eclinic05.herokuapp.com/pricelist/getPriceList").subscribe(
      res =>{
        this.dataSource.paginator = this.paginator;
        // @ts-ignore
        this.dataSource.data = res;
      }
    );
  }


  deletePrice(element: any) {
    this.currentlySelected.id = null;
    this.currentlySelected.apt_type = '';
    this.currentlySelected.price = null;
    let params = new HttpParams().set("apt_type", element.apt_type.toString());
    this.http.delete("https://eclinic05.herokuapp.com/pricelist/deletePrice",{params:params}).subscribe(
      res =>{
        let index = this.dataSource.data.indexOf(element);
        this.dataSource.data.splice(index,1);
        this.dataSource._updateChangeSubscription();
        this.isDisabled = true;
        this.expanel.close();
        this._snackBar.open("Price successfully deleted from pricelist.", "Close", {
          duration: 2000,
        });
        this.refreshData();
      }
    );
  }

  selectionChanged(row: any) {
    this.isDisabled = false;
    this.expanel.open();
    this.currentlySelected.id = row.id;
    this.currentlySelected.apt_type = row.apt_type;
    this.currentlySelected.price = row.price;
  }

  addPrice() {
    let url = "https://eclinic05.herokuapp.com/pricelist/addPrice";
    this.http.post(url,this.model).subscribe(
      res => {
        this._snackBar.open("Price successfully added to pricelist.", "Close", {
          duration: 2000,
        });
        this.refreshData()
      });
  }

  refreshData(){
    this.model.id = null;
    this.model.price = 0;
    this.model.apt_type = " ";
    this.http.get("https://eclinic05.herokuapp.com/pricelist/getAptTypes").subscribe(
      res => {
        this.apt_types = res;
      });
    this.http.get("https://eclinic05.herokuapp.com/pricelist/getPriceList").subscribe(
      res =>{
        this.dataSource.paginator = this.paginator;
        // @ts-ignore
        this.dataSource.data = res;
      }
    );
  }

  editPricelist() {
    this.http.post("https://eclinic05.herokuapp.com/pricelist/editPricelist", this.currentlySelected).subscribe(
      res =>{
        this._snackBar.open("Pricelist changed successfully.", "Close", {
          duration: 2000,
        });
        this.http.get("https://eclinic05.herokuapp.com/pricelist/getPriceList").subscribe(
          res =>{
            this.dataSource.paginator = this.paginator;
            // @ts-ignore
            this.dataSource.data = res;
          }
        );
      },
      err => {
        this._snackBar.open("Something went wrong!", "Close", {
          duration: 2000,
        });
      }

    );
  }
}

export interface priceListModel
{
  id: number;
  apt_type: string;
  price: number;
}

