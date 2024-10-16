import {Component, Input, OnInit} from '@angular/core';
import {RouterLink} from "@angular/router";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [
    RouterLink,
    NgIf
  ],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent implements OnInit{
  @Input() isAdmin = false;
  role ='user';

  constructor() {
  }

  ngOnInit(): void {
    if (this.isAdmin) {
      this.role='admin';
    }
  }

}
