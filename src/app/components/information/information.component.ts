import {Component, Input, OnInit} from '@angular/core';
import {NgClass} from "@angular/common";
export interface LightInfoInput{
  title?: string;
  amount?: number;
  infoStyle?: 'bg-primary' | 'bg-success' | 'bg-warning' | 'bg-danger' ;
}
@Component({
  selector: 'app-information',
  standalone: true,
  imports: [
    NgClass
  ],
  templateUrl: './information.component.html',
  styleUrl: './information.component.scss'
})
export class InformationComponent implements OnInit{
  @Input() infoInput: LightInfoInput = {};

  constructor() {
  }
  ngOnInit(): void {
  }

}
