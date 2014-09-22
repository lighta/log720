package demo.grid;

import org.omg.CosNaming.*;
import demo.grid.MyGridPackage.GridException;

public class Client {
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

				// nc.to_name("grid.example");

				org.omg.CORBA.Object o = nc.resolve(nc.to_name("grid.example"));

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
