package structural;


//** Adapter Pattern :
//**Use this pattern when We need to reuse existing interafce or API to write implementation for the new interface.
//**ie. Provide the functionality of the existing API for the singnature of the new API.
//** This ensures that there is no change to the existing code base.
public class Adapter {

    public static void main(String[] args) {

        OldAPI oldApi = new OldAPI();
        NewAPIAdapter newAPIAdapter = new NewAPIAdapter(oldApi);

        System.out.println(newAPIAdapter.getAdvHotelInfo(24));
        System.out.println(newAPIAdapter.getReservationDetails("Helson001"));

    }
}


interface OldReserVationSystem {

    String getHotelInfo(int hotelId);

    void bookReservation(String customer_info);

    String getReservationDetails(String code);

}


class OldAPI implements OldReserVationSystem {

    @Override
    public String getHotelInfo(int hotelId) {
        return "Helson Hotel";
    }

    @Override
    public void bookReservation(String customer_info) {
        System.out.println("The reservation is booked");
    }

    @Override
    public String getReservationDetails(String code) {
        return "Name : helson : Code : " + code;
    }
}


//** No lets say that client wants to adapt new interface with new additional fucntionality aand with diff signatures
//** So we think of giving implementation of this new interface by reusing old API.
interface NewReservationSystem {

    String getAdvHotelInfo(int hotelId);

    void doReserve(String customer_info);

    String getReservationDetails(String code);

    String getAgeOfCustomer(String customer_name);

}

//** Below is the adapter class which reuses the fucntionality of the existing API in the new API Singature.
//** Adapter Variants  : Object based  + Inheritence based
//** Look for majority of the new API methods we have reused the existign API methods.
class NewAPIAdapter implements NewReservationSystem {

    private OldReserVationSystem oldAPI;

    //Constructor for this Adapter takes in concrete class of the OLDAPI
    //*This is Object based.
    NewAPIAdapter(OldReserVationSystem oldAPI) {
        this.oldAPI = oldAPI;
    }

    @Override
    public String getAdvHotelInfo(int hotelId) {
        return oldAPI.getHotelInfo(hotelId);
    }

    @Override
    public void doReserve(String customer_info) {
        oldAPI.bookReservation(customer_info);
    }

    @Override
    public String getReservationDetails(String code) {
        return oldAPI.getReservationDetails(code);
    }

    @Override
    public String getAgeOfCustomer(String customer_name) {
        return String.valueOf(28);
    }
}