package ca.etsmtl.log720.lab1;

import org.omg.CosNaming.*;

import demo.grid.MyGrid;
import demo.grid.MyGridHelper;
import demo.grid.MyGridPackage.GridException;

public class Client_Poste {
	public static void main(String args[]) {
		try {
			MyGrid grid;

			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

			if (args.length == 1) {
				// args[0] is an IOR-string
				grid = MyGridHelper.narrow(orb.string_to_object(args[0]));
			} else {
				NamingContextExt nc = NamingContextExtHelper.narrow(orb
						.resolve_initial_references("NameService"));
				org.omg.CORBA.Object o = nc.resolve(nc.to_name("server_poste"));

				grid = MyGridHelper.narrow(o);
			}

			short x = grid.height();
			System.out.println("Height = " + x);

			short y = grid.width();
			System.out.println("Width = " + y);

			x -= 1;
			y -= 1;

			System.out.println("Old value at (" + x + "," + y + "): "
					+ grid.getElement(x, y));

			System.out.println("Setting (" + x + "," + y + ") to 470.11");

			grid.setElement(x, y, 470.11);

			System.out.println("New value at (" + x + "," + y + "): "
					+ grid.getElement(x, y));

			try {
				grid.opWithException();
			} catch (GridException ex) {
				System.out.println("Exception, reason: " + ex.why);
			}

			orb.shutdown(true);
			System.out.println("done. ");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
