package medmap;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

public class AddMedicineServlet extends HttpServlet {

    public static ArrayList<Medicine> medicines = new ArrayList<>();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String pharmacy = req.getParameter("pharmacy");
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        boolean updated = false;

        for (Medicine m : medicines) {
            if (m.getName().equalsIgnoreCase(name)
                && m.getPharmacy().equalsIgnoreCase(pharmacy)) {
                m.setQuantity(quantity);
                updated = true;
                break;
            }
        }

        if (!updated) {
            medicines.add(new Medicine(name, pharmacy, quantity));
        }

        resp.getWriter().println("Medicine added/updated successfully.");
    }
}
