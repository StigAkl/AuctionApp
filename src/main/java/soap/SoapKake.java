package soap;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import ejb.AuctionDAO;
import entities.Product;

@WebService
public class SoapKake {
	
	@EJB
	private AuctionDAO auctionDao;
	
	@WebMethod(operationName="findProduct")
	public Product findProduct(@WebParam(name="productId") int id) {
		return auctionDao.findProduct(id);
	}
	
	@WebMethod(operationName="findProducts")
	public List<Product> findProducts() {
		return auctionDao.findProducts();
	}
	
	@WebMethod(operationName="addBid")
	public String addBid(@WebParam(name="bid") int bid,@WebParam(name="productId") int id) {
		return auctionDao.addBid("haakon@hotmail", bid, id);
	}

}
