package com.springboot.jpa.front_end;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpa.models.Banner;
import com.springboot.jpa.models.Bill;
import com.springboot.jpa.models.Color;
import com.springboot.jpa.models.Customer;
import com.springboot.jpa.models.Detail;
import com.springboot.jpa.models.Gender;
import com.springboot.jpa.models.Image;
import com.springboot.jpa.models.Producer;
import com.springboot.jpa.models.Product;
import com.springboot.jpa.models.Size;
import com.springboot.jpa.models.Type;
import com.springboot.jpa.service.BannerService;
import com.springboot.jpa.service.BillService;
import com.springboot.jpa.service.ColorService;
import com.springboot.jpa.service.CustomerService;
import com.springboot.jpa.service.DetailService;
import com.springboot.jpa.service.ImageService;
import com.springboot.jpa.service.ProducerService;
import com.springboot.jpa.service.ProductService;
import com.springboot.jpa.service.SizeService;
import com.springboot.jpa.service.TypeService;

@CrossOrigin
@RestController
@RequestMapping("/front")
public class RestAllApiController {

	@Autowired
	private ProductService productService;

	@Autowired
	private SizeService sizeService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private ProducerService producerService;

	@Autowired
	private BillService billService;

	@Autowired
	private BannerService bannerService;

	@Autowired
	private DetailService detailService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private ColorService colorService;

	// start product ******
	@GetMapping("/products")
	public List<Product> listAll() {
		return this.productService.listAll();
	}
	// search code of product
	@GetMapping("/products/searchName/{name}")
	public List<Product> searchName(@PathVariable("name") String name) {
		return this.productService.findByName(name);
	}

	@GetMapping("products/gender/{gender}")
	public List<Product> getGender(@PathVariable("gender") Gender gender) {
		return this.productService.getGender(gender);
	}

	@GetMapping("/products/{id}")
	public Product getId(@PathVariable("id") Integer id) {
		return productService.findById(id);
	}

	@GetMapping("/products/size/{id}")
	public List<Product> getSize(@PathVariable("id") Integer id) {
		return productService.getSize(id);
	}

	// search code of product
	@GetMapping("/products/searchCode/{code}")
	public List<Product> searchCode(@PathVariable("code") String code) {
		return this.productService.findByCode(code);
	}

	// search min price or max price
	@GetMapping("/products/searchMinMax/{min}/{max}")
	public List<Product> searchMinMaxPrice(@PathVariable("min") Double min, @PathVariable("max") Double max) {
		return this.productService.findbyPriceMinMax(min, max);
	}

	// get product by size
	@GetMapping("/products/searchSize/{id}")
	public List<Product> findProductBySize(@PathVariable("id") Integer sizeId) {
		return this.productService.findProductBySize(sizeId);
	}

	// get product by type
	@GetMapping("/products/searchType/{id}")
	public List<Product> findProductByType(@PathVariable("id") Integer typeId) {
		return this.productService.findProductByType(typeId);
	}

	// get product by color
	@GetMapping("/products/searchColor/{id}")
	public List<Product> findProductByColor(@PathVariable("id") Integer colorId) {
		return this.productService.findProductByColor(colorId);
	}

	// get product by gender
	@GetMapping("/products/searchGender/{num}")
	public List<Product> findProductByGender(@PathVariable("num") Integer number) {
		return this.productService.findProductByGender(number);
	}
	// end product ****

	// start size ****
	@GetMapping("/sizes")
	public List<Size> listAllSizes() {
		return sizeService.listAll();
	}

	// get id size
	@GetMapping("/sizes/{id}")
	public Size getSizeId(@PathVariable("id") Integer id) {
		return sizeService.findById(id);
	}
	// end size ****

	// start type ****
	// get all type of product
	@GetMapping("/types")
	public List<Type> listAllTypes() {
		return typeService.listAll();
	}

	// get id type of product
	@GetMapping("/types/{id}")
	public Type getIdType(@PathVariable("id") Integer id) {
		return typeService.findById(id);
	}

	// end type ****

	// start producer ****
	// get all producer
	@GetMapping("/producers")
	public List<Producer> listAllProducers() {
		return producerService.listAll();
	}

	// get id producer
	@GetMapping("/producers/{id}")
	public Producer getIdProducer(@PathVariable("id") Integer id) {
		return producerService.findById(id);
	}
	// end producer ****

	// start image ****
	// get all image
	@GetMapping("/img")
	public List<Image> listAllImages() {
		return this.imageService.listAll();
	}

	// search image of product
	@GetMapping("/img/getProductById/{id}")
	public List<Image> getProductById(@PathVariable("id") Integer productId) {
		return this.imageService.getProductById(productId);
	}
	// end image ****

	// start banner ****
	// get all banner
	@GetMapping("/banners")
	public List<Banner> listAllBanners() {
		return bannerService.listAll();
	}

	// get id banner
	@GetMapping("/banners/{id}")
	public Banner getIdBanner(@PathVariable("id") Integer id) {
		return bannerService.findById(id);
	}
	// end banner ****

	// start color ****
	// get all color
	@GetMapping("/colors")
	public List<Color> listAllColors() {
		return colorService.listAll();
	}

	// get id color
	@GetMapping("/colors/{id}")
	public Color getIdColor(@PathVariable("id") Integer id) {
		return colorService.findById(id);
	}
	// end color ****

	// start bill ****
	@GetMapping("/bills")
	public List<Bill> listAllBills() {
		return this.billService.listAll();
	}

	// get id bill
	@GetMapping("/bills/{id}")
	public Bill getIdBill(@PathVariable("id") Integer id) {
		return billService.findById(id);
	}

	// get list bill by customer id
	@GetMapping("/bills/findByCusId/{customerId}")
	public List<Bill> findByCustomer(@PathVariable("customerId") Integer customerId) {
		return this.billService.findByCustomer(customerId);
	}

	// update bill
	@PutMapping(value = "/bills", consumes = "multipart/form-data")
	public Bill updateBillId(@RequestParam("id") Integer billId,
			@RequestParam("deliveryAddress") String deliveryAddress) {
		Bill bill = billService.findById(billId);
		bill.setDeliveryAddress(deliveryAddress);
		bill = billService.save(bill);
		bill.setCode("MS0" + billId);
		this.billService.save(bill);
		return bill;
	}

	// delete bill
	@DeleteMapping("/bills/{id}")
	public void deleteBillId(@PathVariable("id") Integer id) {
		Bill bill = billService.findById(id);
		billService.deleteById(bill.getId());
	}

	// add bill
	@PostMapping(value = "/bills", consumes = "multipart/form-data")
	public Bill saveBill(@RequestParam("deliveryAddress") String deliveryAddress,
			@RequestParam("customer") Integer customerId) {
		Bill bill = new Bill();
		Customer cus = customerService.findById(customerId);
		bill.setCreatedDate(LocalDateTime.now());
		bill.setDeliveryAddress(deliveryAddress);
		bill.setCustomer(cus);
		bill = billService.save(bill);
		bill.setCode("MS0" + bill.getId());
		this.billService.save(bill);
		return bill;
	}
	// end bill ****

	// start detail ****
	@GetMapping("/details")
	public List<Detail> listAllDetails() {
		return this.detailService.listAll();
	}

	// get id detail
	@GetMapping("/details/{id}")
	public Detail getIdDetail(@PathVariable("id") Integer id) {
		return detailService.findById(id);
	}

	// find by Bill id
	@GetMapping("/details/findBillId/{billId}")
	public List<Detail> findByBillId(@PathVariable("billId") Integer billId) {
		return this.detailService.findByBillId(billId);
	}

	// add detail
	@PostMapping(value = "/details", consumes = "multipart/form-data")
	public Detail saveDetail(@RequestParam("quantity") Integer quantity, @RequestParam("price") Double price,
			@RequestParam("product") Integer productId, @RequestParam("bill") Integer billId) {
		Product product = productService.findById(productId);
		Bill bill = billService.findById(billId);
		Detail detail = new Detail();
		detail.setBill(bill);
		detail.setProduct(product);
		detail.setPrice(price);
		detail.setQuantity(quantity);
		return detailService.save(detail);
	}

	// delete detail
	@DeleteMapping("/details/{id}")
	public void deleteDetailId(@PathVariable("id") Integer id) {
		Detail detail = detailService.findById(id);
		detailService.deleteById(detail.getId());
	}

	// update detail
	@PutMapping(value = "/details", consumes = "multipart/form-data")
	public Detail updateDetail(@RequestParam("id") Integer id, @RequestParam("quantity") Integer quantity,
			@RequestParam("price") Double price, @RequestParam("product") Integer productId,
			@RequestParam("bill") Integer billId) {
		Product product = productService.findById(productId);
		Bill bill = billService.findById(billId);
		Detail detail = detailService.findById(id);
		detail.setBill(bill);
		detail.setProduct(product);
		detail.setPrice(price);
		detail.setQuantity(quantity);
		return detailService.save(detail);
	}
	// end detail ****

	// start customer ****

	// get all customer
	@GetMapping("/customers")
	public List<Customer> listAllCustomers() {
		return customerService.listAll();
	}

	// get customer by id
	@GetMapping("/customers/{id}")
	public Customer getIdCustomer(@PathVariable("id") Integer id) {
		return customerService.findById(id);
	}

	// search first name of customer
	@GetMapping("/customers/searchName/{firstName}")
	public List<Customer> findByFirstName(@PathVariable("firstName") String firstName) {
		return customerService.findbyFirstName(firstName);
	}

	// search phone of customer
	@GetMapping("/customers/searchPhone/{phone}")
	public Customer findByPhone(@PathVariable("phone") String phone) {
		return customerService.findByPhone(phone);
	}
	
	@PostMapping(value = "/customersPost", consumes = "multipart/form-data")
	public Customer saveCus(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName,
			@RequestParam("phone") String phone, @RequestParam("email") String email,
			 @RequestParam("address") String address) {
		Customer cus = new Customer();
		cus.setAddress(address);
		cus.setEmail(email);
		cus.setPhone(phone);
		cus.setLastName(lastName);
		cus.setFirstName(firstName);
		return customerService.save(cus);
	}
	// end customer ****
}
