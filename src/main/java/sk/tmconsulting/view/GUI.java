package sk.tmconsulting.view;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;

public class GUI {
    public static void main(String[] args) {
        // Frame
        JFrame hlavneOkno = new JFrame("Nieco o mne"); // vytvorime okno
        hlavneOkno.setMinimumSize(new Dimension(800, 600));
        hlavneOkno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ked kliknem na X na okne (cize vo frame) tak sa zatvori standardne
        hlavneOkno.setLocationRelativeTo(null); // vycentrovanie okna

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(null); // layout pre panel bude null, cize prazdny
        hlavneOkno.setContentPane(panel); // my dany panel pridame do frame

        // Label - Nazov vydavku
        JLabel labelNazovVydavku = new JLabel("Názov výdavku");
        labelNazovVydavku.setBounds(85, 23, 100, 20);
        panel.add(labelNazovVydavku);

        // JText - Nazov vydavku
        JTextField txtNazovVydavku = new JTextField();
        txtNazovVydavku.setBounds(190, 20, 200, 30); // x, y, sirka, vyska
        panel.add(txtNazovVydavku);

        // Label - Cena
        JLabel labelCena = new JLabel("Cena");
        labelCena.setBounds(85, 63, 100, 20);
        panel.add(labelCena);

        // JText - Cena
        JTextField txtCena = new JTextField();
        txtCena.setBounds(190, 60, 200, 30); // x, y, sirka, vyska
        panel.add(txtCena);

        // Label - Kategoria
        JLabel labelKategoria = new JLabel("Kategória");
        labelKategoria.setBounds(85, 103, 100, 20);
        panel.add(labelKategoria);

        // Combobox - Kategoria
        String[] KategoriaOptions = {"POTRAVINY", "PHM", "OBLEČENIE", "KONÍČKY", "INÉ"};
        JComboBox<String> cmbKategoria = new JComboBox<>(KategoriaOptions);
        cmbKategoria.setBounds(190, 103, 100, 20);
        panel.add(cmbKategoria);

        // Label - Datum
        JLabel labelDatum = new JLabel("Dátum");
        labelDatum.setBounds(85, 143, 100, 20);
        panel.add(labelDatum);

        // Calendar - date picker
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        datePicker.setBounds(190, 143, 150, 30);
        panel.add(datePicker);

        // Display
        hlavneOkno.pack();
        hlavneOkno.setVisible(true);
    }
}
