import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

const SIZES: string[] = [
  'Personal - 6 inches',
  'Regular - 9 inches',
  'Large - 12 inches',
  'Extra Large - 15 inches',
];

const PIZZA_TOPPINGS: string[] = [
  'chicken',
  'seafood',
  'beef',
  'vegetables',
  'cheese',
  'arugula',
  'pineapple',
];

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
})
export class MainComponent implements OnInit {
  pizzaFormBuilder = inject(FormBuilder);
  pizzaFormGroup!: FormGroup; // form will definitely have a FormGroup

  pizzaSize = SIZES[0]; // chuk setting a default pizza size

  constructor() {}

  // create a reactive form on initialisation of the page
  ngOnInit(): void {
    this.pizzaFormGroup = this.createForm();
  }

  updateSize(size: string): void {
    this.pizzaSize = SIZES[parseInt(size)];
  }

  createForm(): FormGroup {
    // create a form builder group, then
    let toppingsGroup = this.pizzaFormBuilder.group({});

    // iterate through each topping in the topping list to create controls
    // formGroupName needs to be specified in the HTML for the for group
    PIZZA_TOPPINGS.forEach((topping) => {
      toppingsGroup.addControl(
        topping,
        this.pizzaFormBuilder.control<boolean>(false, [Validators.required])
      );
    });

    // the resulting form group that will be matched with the component
    // things in here need to match the "formControlName" in the html
    return this.pizzaFormBuilder.group({
      name: this.pizzaFormBuilder.control<string>('', [Validators.required]),
      email: this.pizzaFormBuilder.control<string>('', [
        Validators.required,
        Validators.email,
      ]),
      size: this.pizzaFormBuilder.control<number>(0, [Validators.required]),
      base: this.pizzaFormBuilder.control<string>('', [Validators.required]),
      sauce: this.pizzaFormBuilder.control<string>('', [Validators.required]),
      toppings: toppingsGroup,
      comments: this.pizzaFormBuilder.control<string>(''),
    });
  }

  placeOrder(): void {
    console.log('Order submit button clicked.');
  }
}
