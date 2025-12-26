package com.example.naturalfarm;

import com.example.naturalfarm.model.CartItem;
import com.example.naturalfarm.model.Order;
import com.example.naturalfarm.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class StoreController {

    // --- In-Memory Data Store ---
    private final Map<String, Product> products = initializeProducts();
    private final List<CartItem> cart = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();

    private Map<String, Product> initializeProducts() {
        return Map.ofEntries(
                // Veggies
                Map.entry("tomato", new Product("tomato", "Tomato", "RS-30/kg", "https://i.pinimg.com/736x/3b/dc/11/3bdc11ad8e98bb35a58907b1af5a25cf.jpg")),
                Map.entry("brinjal", new Product("brinjal", "Brinjal", "RS-40/kg", "https://i.pinimg.com/1200x/4b/08/55/4b0855b16dd89e49735ead79e2a8da6e.jpg")),
                Map.entry("cauliflower", new Product("cauliflower", "Cauliflower", "RS-35/kg", "https://i.pinimg.com/736x/cc/5a/2a/cc5a2a3b15e947c649563dcb7bc6e191.jpg")),
                Map.entry("cabbage", new Product("cabbage", "Cabbage", "RS-30/kg", "https://i.pinimg.com/1200x/b1/2a/53/b12a532fa575f03b3be647bdf5ae0192.jpg")),
                Map.entry("potato", new Product("potato", "Potato", "RS-25/kg", "https://i.pinimg.com/736x/8d/43/31/8d4331f384ad7699ff511251bd0c11e2.jpg")),
                Map.entry("onion", new Product("onion", "Onion", "RS-30/kg", "https://i.pinimg.com/736x/f9/97/38/f99738883642acea3c800caff14d66f2.jpg")),
                Map.entry("carrot", new Product("carrot", "Carrot", "RS-50/kg", "https://i.pinimg.com/1200x/13/46/be/1346be25f04e26e48b62c2040fbe3109.jpg")),
                Map.entry("spinach", new Product("spinach", "Spinach", "RS-20/bunch", "https://i.pinimg.com/736x/b1/a8/51/b1a85141c5e0f1f493480f3ea2a19e52.jpg")),
                Map.entry("broccoli", new Product("broccoli", "Broccoli", "RS-80/each", "https://i.pinimg.com/736x/9a/6c/88/9a6c88ac91607d0b54baa3799651b586.jpg")),
                Map.entry("cucumber", new Product("cucumber", "Cucumber", "RS-30/kg", "https://i.pinimg.com/736x/08/7a/f3/087af30428c9bc3acffa80d0f342de6a.jpg")),
                Map.entry("capsicum", new Product("capsicum", "Capsicum", "RS-60/kg", "https://i.pinimg.com/736x/10/f7/16/10f716b3535cfb9c6fb2c24ead9d023f.jpg")),
                Map.entry("ginger", new Product("ginger", "Ginger", "RS-100/kg", "https://i.pinimg.com/736x/07/8f/a7/078fa7ddf445e295651f7eb9d2ff4d07.jpg")),
                Map.entry("garlic", new Product("garlic", "Garlic", "RS-120/kg", "https://i.pinimg.com/736x/a3/b6/e8/a3b6e8aedc25b2c9a3b12823d80a5c52.jpg")),
                Map.entry("lemon", new Product("lemon", "Lemon", "RS-5/each", "https://i.pinimg.com/1200x/e8/a4/56/e8a456a91a265425a8e7cb7ef9b64b5c.jpg")),

                // Fruits
                Map.entry("apple", new Product("apple", "Apple", "RS-120/kg", "https://i.pinimg.com/1200x/cf/39/c9/cf39c9a9789abc51124191dcea5d700f.jpg")),
                Map.entry("banana", new Product("banana", "Banana", "RS-50/dozen", "https://i.pinimg.com/736x/79/c9/2a/79c92ab7a664fda3188761767e957d73.jpg")),
                Map.entry("grapes", new Product("grapes", "Grapes", "RS-80/kg", "https://i.pinimg.com/736x/c0/3f/a8/c03fa81c09c03831858f327159bd3ebe.jpg")),
                Map.entry("green-grapes", new Product("green-grapes", "Green Grapes", "RS-90/kg", "https://i.pinimg.com/736x/43/ec/d5/43ecd57661bbc7419de1d170d18e8899.jpg")),
                Map.entry("mango", new Product("mango", "Mango", "RS-100/kg", "https://i.pinimg.com/736x/2b/a8/c1/2ba8c147b43021384db3e258bb75d41a.jpg")),
                Map.entry("orange", new Product("orange", "Orange", "RS-70/kg", "https://i.pinimg.com/736x/8d/15/dc/8d15dc93a7a70a676ca4e833578a16f9.jpg")),
                Map.entry("watermelon", new Product("watermelon", "Watermelon", "RS-40/kg", "https://i.pinimg.com/736x/9d/d7/8d/9dd78d66ccc3cf73c9ebf6f703ce2517.jpg")),
                Map.entry("pineapple", new Product("pineapple", "Pineapple", "RS-60/each", "https://i.pinimg.com/736x/5a/a7/11/5aa7118780198a048c54e472039f6464.jpg")),
                Map.entry("pomegranate", new Product("pomegranate", "Pomegranate", "RS-150/kg", "https://i.pinimg.com/736x/0f/49/6a/0f496a1e2ae70190835653363dc81b1d.jpg")),
                Map.entry("papaya", new Product("papaya", "Papaya", "RS-40/kg", "https://i.pinimg.com/1200x/3d/c9/ce/3dc9cea802e451790e5b22d5fa31f6c6.jpg")),
                Map.entry("guava", new Product("guava", "Guava", "RS-50/kg", "https://i.pinimg.com/736x/af/f3/00/aff300323e47b2dc73659975d22c4916.jpg")),
                Map.entry("kiwi", new Product("kiwi", "Kiwi", "RS-30/each", "https://i.pinimg.com/736x/c2/65/44/c265446aa67af36c78de356052597503.jpg")),
                Map.entry("strawberry", new Product("strawberry", "Strawberry", "RS-200/box", "https://i.pinimg.com/736x/1c/ac/37/1cac37c5520085aba52654b18beb225a.jpg")),
                Map.entry("cherry", new Product("cherry", "Cherry", "RS-300/box", "https://i.pinimg.com/1200x/d6/b8/85/d6b8859894470e7b7c0e854f1dba7283.jpg")),
                
                // Flowers
                Map.entry("rose", new Product("rose", "Rose", "RS-20/each", "https://i.pinimg.com/736x/a3/1b/f2/a31bf2e059e264ad1d9f76ad2d3af45e.jpg")),
                Map.entry("hibiscus", new Product("hibiscus", "Hibiscus", "RS-15/each", "https://i.pinimg.com/736x/3d/4d/c5/3d4dc56c2fe90ba1d071fca39ae56dff.jpg")),
                Map.entry("jasmine", new Product("jasmine", "Jasmine", "RS-30/string", "https://i.pinimg.com/736x/d9/78/60/d97860452b8f11960124aa55a2d5f9ea.jpg")),
                Map.entry("lilly", new Product("lilly", "Lilly", "RS-25/each", "https://i.pinimg.com/736x/e4/a3/0c/e4a30cec56f003a30ecdf8a395d67075.jpg")),
                Map.entry("marigold", new Product("marigold", "Marigold", "RS-40/string", "https://i.pinimg.com/736x/f1/32/84/f132845b46c26d38cd8272e9e3104e68.jpg")),
                Map.entry("sunflower", new Product("sunflower", "Sunflower", "RS-30/each", "https://i.pinimg.com/1200x/6a/f6/23/6af623d1790127c36e234c012a08bbe8.jpg")),
                Map.entry("tulip", new Product("tulip", "Tulip", "RS-35/each", "https://i.pinimg.com/736x/93/b8/fe/93b8fe00a249a31a5c1be7dd0da49583.jpg")),
                Map.entry("daisy", new Product("daisy", "Daisy", "RS-10/each", "https://i.pinimg.com/736x/a8/58/e1/a858e1d5d9bfce68386e5af054decffa.jpg")),
                Map.entry("orchid", new Product("orchid", "Orchid", "RS-150/stick", "https://i.pinimg.com/1200x/67/5e/4a/675e4aa192e96814d681bbd23b3c0eb1.jpg")),
                Map.entry("lotus", new Product("lotus", "Lotus", "RS-50/each", "https://i.pinimg.com/736x/85/28/ad/8528ad2465babc2b3e89e462fc11d695.jpg")),
                Map.entry("carnation", new Product("carnation", "Carnation", "RS-20/each", "https://i.pinimg.com/736x/a3/4e/36/a34e3681ccddabbf5c4d58eb6f23f4c8.jpg")),
                Map.entry("dahlia", new Product("dahlia", "Dahlia", "RS-25/each", "https://i.pinimg.com/736x/af/3c/de/af3cde6a927897d7009a083533bf7500.jpg")),
                Map.entry("lavender", new Product("lavender", "Lavender", "RS-80/bunch", "https://i.pinimg.com/736x/a1/42/bb/a142bb4029250b8228bc089d01dfb178.jpg")),
                Map.entry("aster", new Product("aster", "Aster", "RS-15/each", "https://i.pinimg.com/736x/dc/90/7a/dc907af150873b9afe0130d229f4a55c.jpg")),
                
                // Eggs & Honey
                Map.entry("eggs", new Product("eggs", "Farm Eggs", "RS-10/each", "https://i.pinimg.com/736x/84/13/66/841366ba2aad4af8a12f71714d52f5e7.jpg")),
                Map.entry("natural-eggs", new Product("natural-eggs", "Natural Eggs", "RS-12/each", "https://i.pinimg.com/736x/60/3f/5f/603f5fb70c1f59820126f69e2ea26303.jpg")),
                Map.entry("natural-honey", new Product("natural-honey", "Natural Honey", "RS-250/bottle", "https://i.pinimg.com/1200x/5c/95/8e/5c958ef9d9590865c43a271bf9d9f9f7.jpg")),
                Map.entry("75-honey", new Product("75-honey", "75% Natural Honey", "RS-180/bottle", "https://i.pinimg.com/736x/34/e6/5b/34e65bf0e8bcb329b2f9eba402490b4e.jpg"))
        );
    }

    private final Map<String, List<String>> categories = Map.of(
            "veggies", List.of("tomato", "brinjal", "cauliflower", "cabbage", "potato", "onion", "carrot", "spinach", "broccoli", "cucumber", "capsicum", "ginger", "garlic", "lemon"),
            "fruits", List.of("apple", "banana", "grapes", "green-grapes", "mango", "orange", "watermelon", "pineapple", "pomegranate", "papaya", "guava", "kiwi", "strawberry", "cherry"),
            "flowers", List.of("rose", "hibiscus", "jasmine", "lilly", "marigold", "sunflower", "tulip", "daisy", "orchid", "lotus", "carnation", "dahlia", "lavender", "aster"),
            "eggs", List.of("eggs", "natural-eggs"),
            "honey", List.of("natural-honey", "75-honey")
    );

    @GetMapping("/")
    @ResponseBody
    public String handleRequest(
            @RequestParam(required = false) String view,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String product,
            @RequestParam(required = false) String action) {

        if ("addToCart".equals(action) && product != null) {
            CartItem existingItem = cart.stream()
                    .filter(item -> item.getProduct().getId().equals(product))
                    .findFirst()
                    .orElse(null);

            if (existingItem != null) {
                 cart.add(new CartItem(products.get(product), 1));
            } else {
                cart.add(new CartItem(products.get(product), 1));
            }
        }
        if ("placeOrder".equals(action)) {
            String paymentMethod = "Unknown";
            if (view != null) {
                if (view.contains("cod")) paymentMethod = "Cash on Delivery";
                if (view.contains("upi")) paymentMethod = "UPI";
                if (view.contains("card")) paymentMethod = "Card";
            }
            if (!cart.isEmpty()) {
                orders.add(new Order(UUID.randomUUID().toString().substring(0, 8), new ArrayList<>(cart), paymentMethod));
                cart.clear();
            }
        }

        if ("removeFromCart".equals(action) && product != null) {
            CartItem itemToRemove = cart.stream()
                    .filter(item -> item.getProduct().getId().equals(product))
                    .findFirst()
                    .orElse(null);
            if (itemToRemove != null) {
                cart.remove(itemToRemove);
            }
        }

        String contentHtml;
        if ("category".equals(view) && category != null) {
            contentHtml = generateCategoryPageHtml(category);
        } else if ("detail".equals(view) && product != null) {
            contentHtml = generateDetailPageHtml(product);
        } else if ("cart".equals(view)) {
            contentHtml = generateCartPageHtml();
        } else if ("orders".equals(view)) {
            contentHtml = generateOrdersPageHtml();
        } else if ("payment".equals(view)) {
            contentHtml = generatePaymentPageHtml();
        } else if ("upi".equals(view)) {
            contentHtml = generateUpiOptionsPageHtml();
        } else if ("upi-id".equals(view)) {
            contentHtml = generateUpiIdPageHtml();
        } else if ("card".equals(view)) {
            contentHtml = generateCardPageHtml();
        } else if ("order-placed".equals(view)) {
            contentHtml = generateOrderPlacedPageHtml();
        } else {
            contentHtml = generateHomePageHtml();
        }
        return generateFullPage(contentHtml);
    }

    private String generateHomePageHtml() {
        return """
                <div class='home-banner'>
                    <h1 class='banner-title'>🌿 Welcome to Natural Farms 🌿</h1>
                    <p class='banner-text'>Fresh. Organic. Delivered to Your Door</p>
                </div>
                <div class='promo-banner'>
                    <span class='promo-badge'>🎁 LIMITED OFFER</span>
                    <p class='promo-text'>Get 20% OFF on your first order!</p>
                </div>
                <div class='home-grid' role='navigation' aria-label='Categories'>
                  <a class='home-card' href='/?view=category&category=veggies'>
                    <img src='https://i.pinimg.com/736x/b6/f6/d0/b6f6d0c0a23ab158f8a69a516e2171a6.jpg' alt='Veggies'>
                    <span class='label'>VEGGIES</span>
                  </a>
                  <a class='home-card' href='/?view=category&category=fruits'>
                    <img src='https://i.pinimg.com/736x/d9/8e/9e/d98e9eeaf85de629a73142a8e40abb7a.jpg' alt='Fruits'>
                    <span class='label'>FRUITS</span>
                  </a>
                  <a class='home-card' href='/?view=category&category=flowers'>
                    <img src='https://i.pinimg.com/736x/16/d3/20/16d3204d469bf8125a2a73a6d9291bd4.jpg' alt='Flowers'>
                    <span class='label'>FLOWERS</span>
                  </a>
                  <a class='home-card' href='/?view=category&category=eggs'>
                    <img src='https://i.pinimg.com/736x/6f/93/f5/6f93f5bba37f19628fd29e05b2fadfdd.jpg' alt='Eggs'>
                    <span class='label'>EGGS</span>
                  </a>
                  <a class='home-card' href='/?view=category&category=honey'>
                    <img src='https://i.pinimg.com/736x/86/20/d1/8620d1668b49bc93e099d7b24ff36e40.jpg' alt='Honey'>
                    <span class='label'>HONEY</span>
                  </a>
                </div>
                <div class='features-section'>
                    <div class='feature-box'>
                        <span class='feature-icon'>✓</span>
                        <p>100% Organic</p>
                    </div>
                    <div class='feature-box'>
                        <span class='feature-icon'>🚚</span>
                        <p>Fast Delivery</p>
                    </div>
                    <div class='feature-box'>
                        <span class='feature-icon'>💰</span>
                        <p>Best Prices</p>
                    </div>
                </div>
                <div class='testimonials-section'>
                    <h3 class='section-title'>⭐ Customer Reviews</h3>
                    <div class='testimonial-card'>
                        <p class='testimonial-text'>"Best quality vegetables! Delivered fresh every time."</p>
                        <p class='testimonial-author'>- Priya Sharma</p>
                    </div>
                    <div class='testimonial-card'>
                        <p class='testimonial-text'>"Amazing service and competitive prices!"</p>
                        <p class='testimonial-author'>- Rajesh Kumar</p>
                    </div>
                </div>
                <div class='cta-banner'>
                    <h3>Why Choose Us?</h3>
                    <ul class='cta-list'>
                        <li>✓ Farm to Table Quality</li>
                        <li>✓ Same Day Delivery Available</li>
                        <li>✓ 100% Money Back Guarantee</li>
                        <li>✓ Expert Customer Support</li>
                    </ul>
                </div>""";
    }

    private String generateCategoryPageHtml(String activeCategory) {
        StringBuilder tabsHtml = new StringBuilder("<nav class='category-tabs'>");
        categories.keySet().stream().sorted().forEach(cat -> {
            String activeClass = cat.equals(activeCategory) ? "active" : "";
            tabsHtml.append(String.format("<a href='/?view=category&category=%s' class='tab-button %s'>%s</a>", cat, activeClass, cat.toUpperCase()));
        });
        tabsHtml.append("</nav>");

        StringBuilder productGridHtml = new StringBuilder("<div class='product-grid'>");
        categories.get(activeCategory).forEach(productId -> {
            Product p = products.get(productId);
            productGridHtml.append(String.format("""
                    <div class='product-card'>
                        <div class='product-badge'>Fresh</div>
                        <div class='product-image-wrapper'>
                            <a href='/?view=detail&product=%s'><img src='%s' alt='%s' class='product-img'></a>
                        </div>
                        <div class='product-name'>%s</div>
                        <div class='price-tag'>%s</div>
                        <div class='product-rating'>⭐⭐⭐⭐⭐</div>
                        <div class='product-actions' style='display: flex; gap: 8px;'>
                            <button onclick="window.location.href='/?action=addToCart&product=%s&view=cart'" class='add-to-cart-btn'>🛒 Add</button>
                            <button onclick="window.location.href='/?action=addToCart&product=%s&view=payment'" class='buy-now-btn'>⚡ Buy</button>
                        </div>
                    </div>""", p.getId(), p.getImageUrl(), p.getName(), p.getName(), p.getPrice(), p.getId(), p.getId()));
        });
        productGridHtml.append("</div><div class='page-nav'><a href='javascript:history.back()' class='back-btn'>← Back</a></div>");
        return tabsHtml.toString() + productGridHtml.toString();
    }

    private String generateDetailPageHtml(String productId) {
        Product p = products.get(productId);
        return String.format("""
                <div class='detail-view'>
                    <div class='detail-image-container'>
                        <img src='%s' alt='%s' class='detail-img'>
                    </div>
                    <h2>%s</h2>
                    <div class='rating-stars'>⭐⭐⭐⭐⭐ (128 reviews)</div>
                    <p class='detail-price'>%s</p>
                    <div class='quantity-selector'>
                        <button class='qty-btn'>-</button>
                        <input type='text' value='1' class='qty-input' readonly>
                        <button class='qty-btn'>+</button>
                    </div>
                    <p class='product-info'>✓ 100%% Fresh | ✓ Organic | ✓ Pesticide Free</p>
                    <div class='product-description'>
                        <h4>About this product</h4>
                        <p>Sourced directly from our certified organic farms. Handpicked for quality and freshness. Delivered within 24-48 hours.</p>
                    </div>
                    <div class='product-actions'>
                        <button onclick="window.location.href='/?action=addToCart&product=%s&view=cart'" class='add-to-cart-btn'>Add to Cart</button>
                        <button onclick="window.location.href='/?action=addToCart&product=%s&view=payment'" class='buy-now-btn'>Buy Now</button>
                    </div>
                    <a href='javascript:history.back()' class='back-btn'>Back to Products</a>
                </div>""", p.getImageUrl(), p.getName(), p.getName(), p.getPrice(), p.getId(), p.getId());
    }

    private String generateCartPageHtml() {
        if (cart.isEmpty()) return """
                <div class='empty-cart'>
                    <div class='empty-icon'>🛒</div>
                    <h2>Your Cart is Empty</h2>
                    <p>Add some fresh products to get started!</p>
                    <a href='/' class='back-btn'>Continue Shopping</a>
                </div>""";

        String items = cart.stream().map(item -> String.format("""
                        <div class='cart-item'>
                            <img src='%s' alt='%s' class='cart-item-img'>
                            <div class='cart-item-details'>
                                <h3>%s</h3>
                                <p>%s</p>
                                <div class='item-qty'>Qty: 1</div>
                            </div>
                            <a href='/?action=removeFromCart&product=%s&view=cart' class='remove-btn'>✕</a>
                        </div>
                    """,
                item.getProduct().getImageUrl(),
                item.getProduct().getName(),
                item.getProduct().getName(),
                item.getProduct().getPrice(),
                item.getProduct().getId()
        ))
                .collect(Collectors.joining());

        return String.format("""
                <h2>Your Cart 🛒</h2>
                <div class='cart-list'>%s</div>
                <div class='cart-summary'>
                    <div class='summary-row'>
                        <span>Subtotal:</span>
                        <span>RS-500</span>
                    </div>
                    <div class='summary-row'>
                        <span>Delivery:</span>
                        <span>FREE</span>
                    </div>
                    <div class='summary-row total'>
                        <span>Total:</span>
                        <span>RS-500</span>
                    </div>
                </div>
                <a href='/?view=payment' class='checkout-btn'>Proceed to Checkout →</a>""", items);
    }

    private String generateOrdersPageHtml() {
        if (orders.isEmpty()) return """
                <div class='empty-orders'>
                    <div class='empty-icon'>📦</div>
                    <h2>No Orders Yet</h2>
                    <p>Start shopping today and see your orders here!</p>
                </div>""";
        String orderBlocks = orders.stream().map(order -> {
            String items = order.getItems().stream().map(item -> String.format("<li>%s</li>", item.getProduct().getName())).collect(Collectors.joining());
            return String.format("""
                    <div class='order-block'>
                        <div class='order-header'>
                            <h3>Order #%s</h3>
                            <span class='status-badge'>Delivered</span>
                        </div>
                        <p>📅 %s | 💳 %s</p>
                        <ul>%s</ul>
                    </div>
                    """, order.getOrderId(), order.getOrderDate(), order.getPaymentMethod(), items);
        }).collect(Collectors.joining());
        return "<h2>Your Orders 📦</h2>" + orderBlocks;
    }

    private String generatePaymentPageHtml() {
        return """
                <div class='payment-form'>
                    <h2>💳 Select Payment Method</h2>
                    <div class='radio-group'>
                        <label class='payment-option payment-cod'><input type='radio' name='payment' value='cod' onclick="document.getElementById('nextBtn').href='/?action=placeOrder&view=order-placed&method=cod'"> 
                            <span class='payment-icon'>🚚</span>
                            <span class='payment-label'>Cash on Delivery</span>
                            <span class='payment-desc'>Pay when your order arrives</span>
                        </label>
                        <label class='payment-option payment-upi'><input type='radio' name='payment' value='upi' onclick="document.getElementById('nextBtn').href='/?view=upi'"> 
                            <span class='payment-icon'>📱</span>
                            <span class='payment-label'>UPI Payment</span>
                            <span class='payment-desc'>Fast & Secure</span>
                        </label>
                        <label class='payment-option payment-card'><input type='radio' name='payment' value='card' onclick="document.getElementById('nextBtn').href='/?view=card'"> 
                            <span class='payment-icon'>💳</span>
                            <span class='payment-label'>Credit/Debit Card</span>
                            <span class='payment-desc'>All major cards accepted</span>
                        </label>
                    </div>
                    <div class='secure-badge'>🔒 Your payment is secured</div>
                    <a href='#' id='nextBtn' class='next-btn'>Next →</a>
                </div>""";
    }

    private String generateUpiOptionsPageHtml() {
        return """
                <div class='payment-form'>
                    <h2>📱 Select UPI App</h2>
                    <div class='radio-group'>
                        <label class='payment-option'><input type='radio' name='upi_app' value='gpay'> 
                            <span class='upi-icon'>🔵</span> Google Pay
                        </label>
                        <label class='payment-option'><input type='radio' name='upi_app' value='phonepe'> 
                            <span class='upi-icon'>🟣</span> PhonePe
                        </label>
                        <label class='payment-option'><input type='radio' name='upi_app' value='paytm'> 
                            <span class='upi-icon'>🔷</span> Paytm
                        </label>
                    </div>
                    <a href='/?view=upi-id' class='next-btn'>Proceed to Pay →</a>
                </div>""";
    }

    private String generateUpiIdPageHtml() {
        return """
                <div class='payment-form'>
                    <h2>📱 Enter UPI ID</h2>
                    <input type='text' placeholder='yourname@upi' class='form-input'>
                    <div class='form-help'>Format: username@bankname</div>
                    <a href='/?action=placeOrder&view=order-placed&method=upi' class='next-btn'>Confirm Order ✓</a>
                </div>""";
    }

    private String generateCardPageHtml() {
        return """
                <div class='payment-form'>
                    <h2>💳 Enter Card Details</h2>
                    <input type='text' placeholder='Card Number' class='form-input' maxlength='16'>
                    <div class='form-row'>
                        <input type='text' placeholder='MM/YY' class='form-input' style='width: 48%;'>
                        <input type='text' placeholder='CVC' class='form-input' style='width: 48%;'>
                    </div>
                    <input type='text' placeholder='Cardholder Name' class='form-input'>
                    <div class='ssl-badge'>🔒 SSL Encrypted</div>
                    <a href='/?action=placeOrder&view=order-placed&method=card' class='next-btn'>Confirm Order ✓</a>
                </div>""";
    }

    private String generateOrderPlacedPageHtml() {
        return """
                <div class='order-placed'>
                    <div class='success-icon'>✓</div>
                    <h2>Order Placed Successfully! 🎉</h2>
                    <p>Thank you for shopping with Natural Farms.</p>
                    <div class='order-confirmation'>
                        <p class='order-id'>Order ID: #NF2025001</p>
                        <p class='order-info'>Your fresh products will arrive within 24-48 hours</p>
                    </div>
                    <div class='delivery-tracker'>
                        <div class='tracker-step active'>
                            <div class='tracker-dot'>1</div>
                            <p>Confirmed</p>
                        </div>
                        <div class='tracker-step'>
                            <div class='tracker-dot'>2</div>
                            <p>Packaging</p>
                        </div>
                        <div class='tracker-step'>
                            <div class='tracker-dot'>3</div>
                            <p>In Transit</p>
                        </div>
                        <div class='tracker-step'>
                            <div class='tracker-dot'>4</div>
                            <p>Delivered</p>
                        </div>
                    </div>
                    <a href='/' class='home-btn'>Continue Shopping</a>
                </div>""";
    }

    private String generateFullPage(String contentHtml) {

        int cartCount = cart.size();
        String cartNavText = "CART" + (cartCount > 0 ? String.format(" (%d)", cartCount) : "");

        String tpl = """
                <!DOCTYPE html>
                <html lang='en'>
                <head>
                    <meta charset='UTF-8'>
                    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                    <title>Natural Farms</title>
                    <link rel='stylesheet' href='/Style.css?v=1'>
                    <style>
                      /* DESIGN 1: Promotional Banner */
                      .promo-banner {
                        background: linear-gradient(135deg, #ff6b6b 0%, #ee5a52 100%);
                        color: #fff;
                        padding: 12px;
                        border-radius: 10px;
                        margin: 12px 16px;
                        text-align: center;
                        box-shadow: 0 6px 16px rgba(255,107,107,0.25);
                      }
                      .promo-badge { background: #fff; color: #ff6b6b; padding: 4px 10px; border-radius: 6px; font-weight: 800; font-size: 0.8rem; display: inline-block; }
                      .promo-text { margin: 8px 0 0 0; font-weight: 600; }

                      /* DESIGN 2: Testimonials Section */
                      .testimonials-section {
                        padding: 16px;
                        margin: 12px 16px;
                      }
                      .section-title { font-size: 1.2rem; font-weight: 800; margin: 0 0 12px 0; color: #333; }
                      .testimonial-card {
                        background: #f0f8ff;
                        border-left: 4px solid #337ab7;
                        padding: 12px;
                        border-radius: 8px;
                        margin-bottom: 10px;
                      }
                      .testimonial-text { margin: 0 0 6px 0; font-size: 0.9rem; color: #333; font-style: italic; }
                      .testimonial-author { margin: 0; font-size: 0.8rem; color: #666; font-weight: 600; }

                      /* DESIGN 3: CTA Banner */
                      .cta-banner {
                        background: linear-gradient(135deg, #20c997 0%, #12b886 100%);
                        color: #fff;
                        padding: 16px;
                        margin: 12px 16px;
                        border-radius: 12px;
                        box-shadow: 0 8px 20px rgba(32,201,151,0.25);
                      }
                      .cta-banner h3 { margin: 0 0 12px 0; font-size: 1.1rem; }
                      .cta-list { list-style: none; padding: 0; margin: 0; }
                      .cta-list li { padding: 6px 0; font-size: 0.9rem; font-weight: 500; }

                      /* DESIGN 4: Product Rating Display */
                      .product-rating { color: #ffc107; font-size: 0.85rem; margin: 6px 0; }

                      /* DESIGN 5: Quantity Selector */
                      .quantity-selector {
                        display: flex;
                        align-items: center;
                        gap: 8px;
                        margin: 12px 0;
                        justify-content: center;
                      }
                      .qty-btn {
                        width: 36px;
                        height: 36px;
                        border: 1px solid #ddd;
                        background: #fff;
                        border-radius: 6px;
                        cursor: pointer;
                        font-weight: 600;
                        color: #333;
                        transition: all 0.2s;
                      }
                      .qty-btn:hover { background: #f0f0f0; }
                      .qty-input { width: 50px; text-align: center; border: 1px solid #ddd; border-radius: 6px; padding: 8px; font-weight: 600; }

                      /* DESIGN 6: Cart Summary Box */
                      .cart-summary {
                        background: #f9f9f9;
                        padding: 16px;
                        border-radius: 10px;
                        margin: 16px;
                        border: 1px solid #e0e0e0;
                      }
                      .summary-row { display: flex; justify-content: space-between; padding: 8px 0; border-bottom: 1px solid #eee; font-size: 0.95rem; }
                      .summary-row.total { border-bottom: none; font-weight: 800; color: #28a745; font-size: 1.1rem; }

                      /* DESIGN 7: Delivery Tracker */
                      .delivery-tracker {
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                        padding: 20px 16px;
                        margin: 16px 0;
                        background: #f0f8ff;
                        border-radius: 10px;
                      }
                      .tracker-step { text-align: center; flex: 1; }
                      .tracker-dot {
                        width: 40px;
                        height: 40px;
                        background: #ddd;
                        border-radius: 50%;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                        margin: 0 auto 8px;
                        font-weight: 700;
                        color: #666;
                      }
                      .tracker-step.active .tracker-dot { background: #28a745; color: #fff; }
                      .tracker-step p { margin: 0; font-size: 0.8rem; color: #666; }

                      /* DESIGN 8: Enhanced Payment Options */
                      .payment-option {
                        display: block;
                        padding: 14px;
                        margin: 12px 0;
                        border: 2px solid #e0e0e0;
                        border-radius: 10px;
                        background: #fff;
                        cursor: pointer;
                        transition: all 0.2s;
                      }
                      .payment-option:hover { border-color: #337ab7; background: #f0f8ff; }
                      .payment-icon { font-size: 1.5rem; margin-right: 10px; display: inline-block; }
                      .payment-label { font-weight: 700; color: #333; display: block; }
                      .payment-desc { font-size: 0.8rem; color: #999; display: block; margin-top: 2px; }
                      .secure-badge { text-align: center; color: #28a745; font-size: 0.9rem; margin: 12px 0; font-weight: 600; }

                      /* DESIGN 9: Form Enhancements */
                      .form-help, .ssl-badge { text-align: center; color: #999; font-size: 0.8rem; margin-top: 6px; }
                      .form-row { display: flex; gap: 8px; }

                      /* DESIGN 10: Enhanced Detail Page */
                      .detail-image-container {
                        background: #f9f9f9;
                        padding: 20px;
                        border-radius: 12px;
                        text-align: center;
                        margin-bottom: 12px;
                      }
                      .detail-img { max-width: 200px; max-height: 200px; border-radius: 10px; }
                      .product-description {
                        background: #f0f8f0;
                        padding: 12px;
                        border-radius: 8px;
                        margin: 12px 0;
                        border-left: 4px solid #28a745;
                      }
                      .product-description h4 { margin: 0 0 8px 0; color: #28a745; font-size: 0.95rem; }
                      .product-description p { margin: 0; font-size: 0.85rem; color: #555; }

                      /* Status Badge */
                      .status-badge { background: #28a745; color: #fff; padding: 4px 8px; border-radius: 4px; font-size: 0.75rem; font-weight: 700; }

                      /* Base Styles */
                      .home-banner {
                        background: linear-gradient(135deg, #28a745 0%, #20c997 100%);
                        color: #fff;
                        padding: 24px;
                        border-radius: 12px;
                        margin: 16px;
                        text-align: center;
                        box-shadow: 0 8px 24px rgba(40,167,69,0.2);
                      }
                      .banner-title { font-size: 1.8rem; margin: 8px 0; font-weight: 900; }
                      .banner-text { font-size: 0.95rem; margin: 0; opacity: 0.95; }

                      .features-section {
                        display: flex;
                        gap: 12px;
                        padding: 12px;
                        margin: 12px;
                        justify-content: space-around;
                      }
                      .feature-box {
                        flex: 1;
                        background: #fff;
                        padding: 12px;
                        border-radius: 10px;
                        text-align: center;
                        box-shadow: 0 4px 12px rgba(0,0,0,0.06);
                        border: 1px solid #e0e0e0;
                      }
                      .feature-icon { font-size: 1.8rem; display: block; margin-bottom: 6px; }
                      .feature-box p { margin: 0; font-weight: 700; color: #333; font-size: 0.85rem; }

                      .product-badge {
                        position: absolute;
                        top: 8px;
                        right: 8px;
                        background: #ff6b6b;
                        color: #fff;
                        padding: 4px 8px;
                        border-radius: 6px;
                        font-size: 0.75rem;
                        font-weight: 700;
                        z-index: 10;
                      }
                      .product-card { position: relative; }

                      .home-grid {
                        display: flex;
                        gap: 12px;
                        justify-content: center;
                        align-items: stretch;
                        padding: 16px;
                        margin: 8px 12px;
                        flex-wrap: wrap;
                        max-width: 420px;
                        margin-left: auto;
                        margin-right: auto;
                      }
                      .home-card {
                        display: flex;
                        flex-direction: column;
                        align-items: center;
                        width: 110px;
                        padding: 12px;
                        text-decoration: none;
                        background: #fff;
                        border-radius: 12px;
                        box-shadow: 0 8px 20px rgba(0,0,0,0.06);
                        border: 1px solid rgba(0,0,0,0.04);
                        color: inherit;
                        transition: transform 0.12s ease, box-shadow 0.12s ease, background 0.12s;
                      }
                      .home-card img {
                        width: 72px;
                        height: 72px;
                        object-fit: cover;
                        border-radius: 50%;
                        border: 2px solid rgba(0,0,0,0.04);
                      }
                      .home-card .label {
                        display: block;
                        margin-top: 8px;
                        font-weight: 800;
                        color: #337ab7;
                        letter-spacing: 0.6px;
                        font-size: 0.95rem;
                      }
                      .home-card:hover {
                        transform: translateY(-6px);
                        box-shadow: 0 14px 32px rgba(0,0,0,0.12);
                      }

                      body {
                        background-color: #e9f7f5;
                        background-image: linear-gradient(rgba(255,255,255,0.86), rgba(255,255,255,0.86)), url('https://i.pinimg.com/736x/f8/33/76/f8337684ee6984b2198dacee61479183.jpg');
                        background-size: cover;
                        background-position: center;
                        background-attachment: fixed;
                      }

                      .app-container { background: linear-gradient(180deg, rgba(255,255,255,0.96), rgba(241,247,246,0.98)); border-radius:16px; box-shadow: 0 12px 40px rgba(0,0,0,0.12); margin: 18px auto; max-width: 520px; overflow: hidden; }
                      .add-to-cart-btn { background-color: #28a745 !important; color: #fff !important; border-radius: 8px !important; padding: 8px 12px !important; border: none; cursor: pointer; }
                      .buy-now-btn { background-color: #ff9f1a !important; color: #fff !important; border-radius: 8px !important; padding: 8px 12px !important; border: none; cursor: pointer; }
                      .home-btn, .next-btn { background-color: #337ab7 !important; color: #fff !important; border-radius: 8px; padding: 12px 20px; border: none; cursor: pointer; display: block; text-align: center; text-decoration: none; }
                      .checkout-btn { background-color: #28a745 !important; color: #fff !important; border-radius: 8px; padding: 12px 20px; border: none; cursor: pointer; display: block; text-align: center; text-decoration: none; width: 100%; }
                      .product-card { background: #fff; padding:12px; border-radius:12px; box-shadow: 0 6px 18px rgba(0,0,0,0.06); margin: 14px; }
                      .product-card img { width:100% !important; max-width:150px; display:block; margin:0 auto 8px; border-radius:10px; }
                      .empty-cart, .empty-orders { text-align: center; padding: 40px; background: #f9f9f9; border-radius: 12px; margin: 16px; }
                      .empty-icon { font-size: 3rem; display: block; margin-bottom: 12px; }
                      .order-placed { text-align: center; padding: 40px 20px; background: linear-gradient(135deg, #d4edda 0%, #c3e6cb 100%); border-radius: 12px; margin: 16px; }
                      .success-icon { display: inline-block; width: 60px; height: 60px; background: #28a745; color: #fff; border-radius: 50%; font-size: 2.5rem; line-height: 60px; margin-bottom: 16px; animation: scaleIn 0.6s ease; }
                      .order-confirmation { background: #fff; padding: 16px; border-radius: 8px; margin: 16px 0; }
                      .order-id { font-weight: 800; color: #28a745; margin: 0; }
                      @keyframes scaleIn { from { transform: scale(0); opacity: 0; } to { transform: scale(1); opacity: 1; } }
                      .category-tabs {
                        display: flex;
                        gap: 10px;
                        justify-content: center;
                        align-items: center;
                        padding: 10px;
                        margin: 12px 16px;
                        background: rgba(255,255,255,0.95);
                        border-radius: 12px;
                        box-shadow: 0 6px 18px rgba(0,0,0,0.04);
                        overflow-x: auto;
                        -webkit-overflow-scrolling: touch;
                      }
                      .tab-button {
                        display: inline-block;
                        padding: 8px 14px;
                        border-radius: 20px;
                        background: transparent;
                        color: #444;
                        text-decoration: none;
                        font-weight: 700;
                        border: 1px solid rgba(0,0,0,0.06);
                        white-space: nowrap;
                        transition: transform 0.12s ease, box-shadow 0.12s ease, background 0.12s;
                      }
                      .tab-button:hover { transform: translateY(-2px); background: rgba(0,0,0,0.04); color: #111; }
                      .tab-button.active {
                        background: #337ab7;
                        color: #fff;
                        box-shadow: 0 8px 18px rgba(51,122,183,0.18);
                        transform: translateY(-3px);
                      }
                      header.header { background: rgba(255,255,255,0.95); padding: 12px; }
                      .bottom-nav { background: rgba(255,255,255,0.95); display: flex; justify-content: space-around; padding: 10px; }
                      .nav-item { border-radius: 10px; padding: 8px 10px; margin: 6px; cursor: pointer; }
                      .cart-item { display: flex; gap: 12px; align-items: center; padding: 12px; background: #fff; border-radius: 10px; margin: 12px 16px; box-shadow: 0 2px 8px rgba(0,0,0,0.04); }
                      .cart-item-img { width: 60px; height: 60px; object-fit: cover; border-radius: 8px; }
                      .cart-item-details { flex: 1; }
                      .cart-item-details h3 { margin: 0; font-size: 0.95rem; }
                      .cart-item-details p { margin: 4px 0; font-size: 0.85rem; color: #666; }
                      .item-qty { font-size: 0.8rem; color: #999; }
                      .remove-btn { color: #ff6b6b; font-weight: 700; font-size: 1.2rem; cursor: pointer; }
                      @media (max-width:480px){
                        .home-grid{ gap:10px; justify-content:space-around; }
                        .home-card{ width:92px; padding:8px; }
                        .home-card .label{ font-size:0.86rem; }
                        .app-container{ margin: 8px; }
                        .features-section{ flex-direction: column; }
                      }
                    </style>
                </head>
                <body>
                    <div class='app-container'>
                        <header class='header'>
                            <div class='menu-icon'>☰ </div>
                            <div class='menu-text'>🌿 NATURAL FARMS</div>
                        </header>
                        <main class='content'>
                            {{CONTENT}}
                        </main>
                        <div>
                          <nav class='bottom-nav'>
                                <button onclick="window.location.href='/'" class='nav-item home'>🏠 HOME</button>
                                <button onclick="window.location.href='/?view=cart'" class='nav-item cart'>🛒 {{CART}}</button>
                                <button onclick="window.location.href='/?view=orders'" class='nav-item orders'>📦 ORDERS</button>
                                <button class='nav-item account'>👤 ACCOUNT</button>
                          </nav>
                        </div>
                    </div>
                </body>
                </html>
                """;

        return tpl.replace("{{CONTENT}}", contentHtml).replace("{{CART}}", cartNavText);
    }
}