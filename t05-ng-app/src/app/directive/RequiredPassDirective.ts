import {Directive, Input} from "@angular/core";
import {AbstractControl, NG_VALIDATORS, Validator} from "@angular/forms";

@Directive({
  selector: '[requiredLen]',
  providers: [
    {provide: NG_VALIDATORS,useExisting:RequiredPassDirective, multi: true}
  ]
})
export class RequiredPassDirective implements Validator {
  @Input("requiredLen")
  requiredLen: boolean;

  validate(c:AbstractControl) {

    let value = c.value;
    if (value == null) value = '';
    if ((value.length > 0 && value.length < 8)) {
      return {
        requiredLen: {condition:false}
      };
    }
    return null;
  }

}
