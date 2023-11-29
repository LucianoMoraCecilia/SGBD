package formulario;

import javax.swing.*;
import java.awt.*;

public class CustomComboBoxRenderer extends DefaultListCellRenderer {

	@Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        // Cambiar el color de fondo cuando se pasa el ratón sobre la opción
        if (isSelected) {
            c.setBackground(new Color(250, 131, 129)); // Cambia el color según tus preferencias
        } else {
            c.setBackground(new Color(155, 195, 252  ));
        }

        return c;
    }
	
}
