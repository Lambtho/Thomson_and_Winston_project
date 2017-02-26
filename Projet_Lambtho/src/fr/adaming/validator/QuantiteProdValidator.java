package fr.adaming.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class QuantiteProdValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent composant, Object valueSaisie) throws ValidatorException {

		int saisie = (int) valueSaisie;

		if (saisie > 12) {
			try {
				throw new Exception("Le nombre saisi est supérieur à nos stocks");
			} catch (Exception e) {
				// L'objet FacesMessage sert de bus pour transporter le message
				// d'erreur vers la Facelet
				throw new ValidatorException(new FacesMessage(e.getMessage()));
			}
		}

	}

}
