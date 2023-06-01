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
  fb = inject(FormBuilder);
  form!: FormGroup;

  pizzaSize = SIZES[0];

  // constructor() {}

  ngOnInit(): void {
    this.form = this.createForm();
  }

  updateSize(size: string) {
    this.pizzaSize = SIZES[parseInt(size)];
  }

  createForm() {
    return this.fb.group({
      nric: this.fb.control<string>('', [
        Validators.required,
        Validators.minLength(9),
      ]),
      email: this.fb.control<string>('', [
        Validators.required,
        Validators.minLength(4),
      ]),
      size: this.fb.control<string>('0', [Validators.required]),
      base: this.fb.control<string>('', [Validators.required]),
      sauce: this.fb.control<string>('', [Validators.required]),
      toppings: this.fb.control<string>('', [Validators.required]),
      comments: this.fb.control<string>('', []),
    });
  }
}
