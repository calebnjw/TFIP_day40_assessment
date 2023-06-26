import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { OrderData, PizzaResponse } from './models';

@Injectable()
export class PizzaService {
  http = inject(HttpClient);

  // TODO: Task 3
  // You may add any parameters and return any type from placeOrder() method
  // Do not change the method name
  placeOrder(order: OrderData) {
    return this.http.post<PizzaResponse>('/api/order', order);
  }

  // TODO: Task 5
  // You may add any parameters and return any type from getOrders() method
  // Do not change the method name
  getOrders() {}

  // TODO: Task 7
  // You may add any parameters and return any type from delivered() method
  // Do not change the method name
  delivered() {}
}
