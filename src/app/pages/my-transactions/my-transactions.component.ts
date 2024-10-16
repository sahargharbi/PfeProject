import {Component, OnInit} from '@angular/core';
import {RouterLink} from "@angular/router";
import {TransactionsService} from "../../services/services/transactions.service";
import {TransactionDto} from "../../services/models/transaction-dto";
import {HelperService} from "../../services/helper/helper.service";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-my-transactions',
  standalone: true,
  imports: [
    RouterLink,
    NgForOf
  ],
  templateUrl: './my-transactions.component.html',
  styleUrl: './my-transactions.component.scss'
})
export class MyTransactionsComponent implements OnInit {
  transactions: Array<TransactionDto> = [];
  constructor(
    private transactionService: TransactionsService,
    private helperService: HelperService
  ) {}
  ngOnInit(): void {
    this.transactionService.findAllByUserId({
      "user-id": this.helperService.userId
    }).subscribe({
      next:(data ) =>{
        this.transactions = data;
      }
    });
  }

}
