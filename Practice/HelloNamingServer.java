import java.io.*;
import org.omg.CORBA.*;
import Test.*;
import org.omg.CosNaming.* ;
import org.omg.CosNaming.NamingContextPackage.*;
import java.util.Properties;

public class HelloNamingServer{

	public static void main (String args[]) {
		try{
			NameComponent nc[] = new NameComponent[1];

			// creating and initializing the ORB
			ORB orb = ORB.init(args, null);

			HelloServant helloRef = new HelloServant();

			//connecting the servant to the orb
			orb.connect(helloRef);
			System.out.println("Orb connected." + orb);

			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			System.out.println("Found NameService.");

			//converting reference into a NamingContext reference
			NamingContext rootCtx = NamingContextHelper.narrow(objRef);

			//Context1
			nc[0] = new NameComponent("Context1", "Context");
			NamingContext Context1Ctx = rootCtx.bind_new_context(nc);
			System.out.println("Context 'Context1' added to Name Space.");

			//Sub-Context 2
			nc[0] = new NameComponent("Sub-Context2", "Context");
			NamingContext SubContext2Ctx = Context1Ctx.bind_new_context(nc);
			System.out.println("Context 'Sub-Context2' added to Context1 context.");

			//Object 1
			nc[0] = new NameComponent("Object1", "Object");
			Context1Ctx.rebind(nc, helloRef);
			System.out.println("Object 'Object1' added to Context1.");


			//Context 2
			nc[0] = new NameComponent("Context2", "Context");
			NamingContext Context2Ctx = rootCtx.bind_new_context(nc);
			System.out.println("Context 'Context2' added to Name Space.");

			//Object 2
			nc[0] = new NameComponent("Object2", "Object");
			Context2Ctx.rebind(nc, helloRef);
			System.out.println("Object 'Object2' added to Context2.");

			//Sub-Context 1
			nc[0] = new NameComponent("Sub-Context1", "Context");
			NamingContext SubContext1Ctx = Context2Ctx.bind_new_context(nc);
			System.out.println("Context 'Sub-Context1' added to Context2 context.");

			//Object 3
			nc[0] = new NameComponent("Object3", "Object");
			SubContext1Ctx.rebind(nc, SubContext1Ctx);
			System.out.println("Object 'Object3' added to Sub-Context1");

			//Object 4
			nc[0] = new NameComponent("Object4", "Object");
			Context2Ctx.rebind(nc, helloRef);
			System.out.println("Object 'Object3' added to Context2.");



			//Context 3
			nc[0] = new NameComponent("Context3", "Context");
			NamingContext Context3Ctx = rootCtx.bind_new_context(nc);
			System.out.println("Context 'Context3' added to Name Space.");

			//Object 5
			nc[0] = new NameComponent("Object5", "Object");
			Context3Ctx.rebind(nc, helloRef);
			System.out.println("Object 'Object5' added to Context3.");



			/**
			nc[0] = new NameComponent("Hello", "Context");
			NamingContext HelloCtx = rootCtx.bind_new_context(nc);
			System.out.println("Context 'Hello' added to Name Space.");

			nc[0] = new NameComponent("World", "Object");
			HelloCtx.rebind(nc, helloRef);
			System.out.println("Object 'World' added to Hello Context.");

			nc[0] = new NameComponent("Hello3", "Context");
			NamingContext Hello3Ctx = rootCtx.bind_new_context(nc);
			System.out.println("Context 'Hello3' added to Name Space.");

			nc[0] = new NameComponent("World3", "Object");
			Hello3Ctx.rebind(nc, Hello3Ctx);
			System.out.println("Object 'World3' added to Hello3 Context.");

			nc[0] = new NameComponent("Hello31", "Context");
			NamingContext Hello4Ctx = Hello3Ctx.bind_new_context(nc);
			System.out.println("Context 'Hello31' added to Hello3 context.");

			nc[0] = new NameComponent("Hello2", "Context");
			NamingContext Hello2Ctx = rootCtx.bind_new_context(nc);
			System.out.println("Context 'Hello2' added to Name Space.");

			nc[0] = new NameComponent("Object2", "Object");
			Hello2Ctx.rebind(nc, helloRef);
			System.out.println("Object 'Object2' added to Hello2 Context.");


			**/


			// waiting for invocations from clients
			orb.run();



		} catch (Exception e) {
			System.err.println("Error: "+e);
			e.printStackTrace(System.out);
		}

	}
}
