import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { FormData, OrderData, PizzaResponse } from './models';

@Injectable()
export class PizzaService {
  http = inject(HttpClient);

  // TODO: Task 3
  // You may add any parameters and return any type from placeOrder() method
  // Do not change the method name
  placeOrder(order: FormData) {
    // console.log('OBJECT.KEYS', Object.keys(order.toppings));
    // console.log(
    //   'OBJECT.KEYS.FILTER',
    //   Object.keys(order.toppings).filter((key) => {
    //     console.log('KEY', key);
    //     console.log('VALUE', order.toppings[key]);
    //     return order.toppings[key];
    //   })
    // );

    // need to convert the toppings object to an array of the specific toppings
    const pizzaOrder: OrderData = {
      ...order,
      // iterate through keys, then filter the keys by the value
      toppings: Object.keys(order.toppings).filter(
        // implicit return true / false to decide which items to save to the array
        (key) => order.toppings[key]
      ),
    };

    return this.http.post<PizzaResponse>(
      'http://localhost:8080/api/order',
      pizzaOrder
    );
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
