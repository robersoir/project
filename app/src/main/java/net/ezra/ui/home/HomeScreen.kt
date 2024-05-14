//package net.ezra.ui.home
//
//
//
//
//
//
////noinspection UsingMaterialAndMaterial3Libraries
////noinspection UsingMaterialAndMaterial3Libraries
//import android.annotation.SuppressLint
//import android.content.Intent
//import android.widget.Button
//import androidx.activity.compose.ManagedActivityResultLauncher
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.ActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.animation.core.Animatable
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.material.BottomNavigation
//import androidx.compose.material.BottomNavigationItem
//import androidx.compose.material.CircularProgressIndicator
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.AccountCircle
//import androidx.compose.material.icons.filled.Favorite
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Card
//import androidx.compose.material3.CenterAlignedTopAppBar
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.NavHostController
//import coil.compose.rememberImagePainter
//import com.google.firebase.Firebase
//import com.google.firebase.firestore.firestore
//import com.google.firebase.firestore.ktx.firestore
//import com.google.firebase.firestore.toObject
//import kotlinx.coroutines.tasks.await
//import net.ezra.R
//import net.ezra.navigation.ROUTE_ADD_PRODUCT
//import net.ezra.navigation.ROUTE_ADD_STUDENTS
//import net.ezra.navigation.ROUTE_HOME
//import net.ezra.navigation.ROUTE_LOGIN
//import net.ezra.navigation.ROUTE_SEARCH
//import net.ezra.navigation.ROUTE_VIEW_PROD
//import net.ezra.ui.products.Product
//import net.ezra.ui.products.ProductListItem
//
//
//data class Screen(val title: String, val icon: Int)
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ResourceAsColor")
//@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
//@Composable
//fun HomeScreen(navController: NavHostController) {
//
//    var isDrawerOpen by remember { mutableStateOf(false) }
//
//    val callLauncher: ManagedActivityResultLauncher<Intent, ActivityResult> =
//        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { _ ->
//
//        }
//
//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = {
//                    Text(text = stringResource(id = R.string.apen))
//                },
//                navigationIcon = {
//                    if (!isDrawerOpen) {
//                        IconButton(onClick = { isDrawerOpen = true }) {
//                            Icon(
//                                Icons.Default.Menu,
//                                contentDescription = "Menu",
//                                tint = Color.White
//                                )
//                        }
//                    }
//                },
//
//                actions = {
//                    IconButton(onClick = {
//                        navController.navigate(ROUTE_LOGIN) {
//                            popUpTo(ROUTE_HOME) { inclusive = true }
//                        }
//
//                    }) {
//                        Icon(
//                            imageVector = Icons.Filled.AccountCircle,
//                            contentDescription = null,
//                            tint = Color.White
//                        )
//                    }
//                },
//
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xff0FB06A),
//                    titleContentColor = Color.White,
//
//                )
//
//            )
//        },
//
//        content ={
//
//
//
//
//
//
//
//            data class Product(
//                var id: String = "",
//                val name: String = "",
//                val description: String ="",
//                var things: String = "",
//                val price: Double = 0.0,
//                var imageUrl: String = ""
//
//            )
//
//
//
//            @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//            @OptIn(ExperimentalMaterial3Api::class)
//            @Composable
//            fun ProductListScreen(navController: NavController, products: List<Product>) {
//                var isLoading by remember { mutableStateOf(true) }
//                var productList by remember { mutableStateOf(emptyList<Product>()) }
//                var displayedProductCount by remember { mutableStateOf(1) }
//                var progress by remember { mutableStateOf(0) }
//
//                LaunchedEffect(Unit) {
//                    fetchProducts { fetchedProducts ->
//                        productList = fetchedProducts
//                        isLoading = false
//                    }
//                }
//
//
//                        Column(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .background(Color(0xff9AEDC9))
//                        ) {
//                            if (isLoading) {
//                                // Progress indicator
//                                Box(
//                                    modifier = Modifier.fillMaxSize(),
//                                    contentAlignment = Alignment.Center
//                                ) {
//                                    CircularProgressIndicator(progress = progress / 100f)
//                                    androidx.compose.material.Text(
//                                        text = "Loading... $progress%",
//                                        fontSize = 20.sp
//                                    )
//                                }
//                            } else {
//                                if (productList.isEmpty()) {
//                                    // No products found
//                                    androidx.compose.material.Text(text = "No products found")
//                                } else {
//                                    // Products list
//                                    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
//                                        items(productList.take(displayedProductCount)) { product ->
//                                            ProductListItem(product) {
//                                                navController.navigate("productDetail/${product.id}")
//                                            }
//                                        }
//                                    }
//                                    Spacer(modifier = Modifier.height(16.dp))
//                                    // Load More Button
//                                    if (displayedProductCount < productList.size) {
//                                        Button(
//                                            colors = ButtonDefaults.buttonColors(
//                                                backgroundColor = Color(
//                                                    0xff0FB06A
//                                                )
//                                            ),
//                                            onClick = { displayedProductCount += 1 },
//                                            modifier = Modifier.align(Alignment.CenterHorizontally)
//                                        ) {
//                                            androidx.compose.material.Text(text = "Load More")
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .clickable {
//                        if (isDrawerOpen) {
//                            isDrawerOpen = false
//                        }
//                    }
//            ) {
//
//
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color(0xff9AEDC9)),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//
////                    Text(
////                        text = stringResource(id = R.string.call),
////                        fontSize = 20.sp,
////                        modifier = Modifier
////                            .padding(16.dp)
////                            .clickable {
////
////                                val intent = Intent(Intent.ACTION_DIAL)
////                                intent.data = Uri.parse("tel:+254796759850")
////
////                                callLauncher.launch(intent)
////                            }
////                    )
//
//
//
//                    Spacer(modifier = Modifier.height(15.dp))
//
////                    Text(
////                        modifier = Modifier
////
////                            .clickable {
////                                navController.navigate(ROUTE_LOGIN) {
////                                    popUpTo(ROUTE_HOME) { inclusive = true }
////                                }
////                            },
////                        text = "Login Here",
////                        textAlign = TextAlign.Center,
////                        fontSize = 20.sp,
////                        color = MaterialTheme.colorScheme.onSurface
////                    )
//
//
//
//                    Text(
//                        modifier = Modifier
//
//                            .clickable {
//                                navController.navigate(ROUTE_ADD_PRODUCT) {
//                                    popUpTo(ROUTE_HOME) { inclusive = true }
//                                }
//                            },
//                        text = "Add Products",
//                        textAlign = TextAlign.Center,
//                        fontSize = 20.sp,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
//
//                    Text(
//                        modifier = Modifier
//
//                            .clickable {
//                                navController.navigate(ROUTE_ADD_STUDENTS) {
//                                    popUpTo(ROUTE_HOME) { inclusive = true }
//                                }
//                            },
//                        text = "Add Students",
//                        textAlign = TextAlign.Center,
//                        fontSize = 20.sp,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
//
//                    Text(
//                        modifier = Modifier
//
//                            .clickable {
//                                navController.navigate(ROUTE_VIEW_PROD) {
//                                    popUpTo(ROUTE_HOME) { inclusive = true }
//                                }
//                            },
//                        text = "view Products",
//                        textAlign = TextAlign.Center,
//                        fontSize = 20.sp,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
//
//
//
//                    Spacer(modifier = Modifier.height(15.dp))
//
//                    Text(
//                        text = "You're welcome",
//                        fontSize = 30.sp,
//                        color = Color.White
//                    )
//
//
//
//                }
//
//            }
//
//        },
//
//        bottomBar = { BottomBar(navController = navController) }
//
//
//
//
//
//
//
//    )
//
//    AnimatedDrawer(
//        isOpen = isDrawerOpen,
//        onClose = { isDrawerOpen = false }
//    )
//}
//
//
//
//@Composable
//fun ProductListItem(product: Product, onItemClick: (String) -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .clickable { onItemClick(product.id) }
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.padding(16.dp)
//        ) {
//            // Product Image
//            Image(
//                painter = rememberImagePainter(product.imageUrl),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(60.dp)
//            )
//
//            Spacer(modifier = Modifier.width(16.dp))
//
//            // Product Details
//            Column {
//                androidx.compose.material.Text(text = product.name)
//                androidx.compose.material.Text(text = "Price: ${product.price}")
//                androidx.compose.material.Text(text = product.things)
//            }
//        }
//    }
//}
//
//private suspend fun fetchProducts(onSuccess: (List<Product>) -> Unit) {
//    val firestore = Firebase.firestore
//    val snapshot = firestore.collection("products").get().await()
//    val productList = snapshot.documents.mapNotNull { doc ->
//        val product = doc.toObject<Product>()
//        product?.id = doc.id
//        product
//    }
//    onSuccess(productList)
//}
//
//suspend fun fetchProduct(productId: String, onSuccess: (Product?) -> Unit) {
//    val firestore = com.google.firebase.ktx.Firebase.firestore
//    val docRef = firestore.collection("products").document(productId)
//    val snapshot = docRef.get().await()
//    val product = snapshot.toObject<Product>()
//    onSuccess(product)
//}
//
//
//
//@Composable
//fun AnimatedDrawer(isOpen: Boolean, onClose: () -> Unit) {
//    val drawerWidth = remember { Animatable(if (isOpen) 250f else 0f) }
//
//    LaunchedEffect(isOpen) {
//        drawerWidth.animateTo(if (isOpen) 250f else 0f, animationSpec = tween(durationMillis = 300))
//    }
//
//    Surface(
//        modifier = Modifier
//            .fillMaxHeight()
//            .width(drawerWidth.value.dp)
//            ,
//        color = Color.LightGray,
////        elevation = 16.dp
//    ) {
//        Column {
//            Text(
//                text = "Drawer Item 1"
//
//            )
//            Text(
//                text = "Drawer Item 2"
//            )
//            Text(
//                text = "Drawer Item 3",
//                modifier = Modifier.clickable {  }
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            Text(text = stringResource(id = R.string.developer))
//
//        }
//    }
//}
//
//
//
//
//
//
//@Composable
//fun BottomBar(navController: NavController) {
//    val selectedIndex = remember { mutableStateOf(0) }
//    BottomNavigation(
//        elevation = 10.dp,
//        backgroundColor = Color(0xff0FB06A)
//
//
//    ) {
//
//        BottomNavigationItem(icon = {
//            Icon(imageVector = Icons.Default.Home,"", tint = Color.White)
//        },
//            label = { Text(text = "Home",color =  Color.White) }, selected = (selectedIndex.value == 0), onClick = {
//
//            })
//
//        BottomNavigationItem(icon = {
//            Icon(imageVector = Icons.Default.Favorite,"",tint = Color.White)
//        },
//            label = { Text(text = "Favorite",color =  Color.White) }, selected = (selectedIndex.value == 1), onClick = {
//
//            })
//
//        BottomNavigationItem(icon = {
//            Icon(imageVector = Icons.Default.Person, "",tint = Color.White)
//        },
//            label = { Text(
//                text = "Students",
//                color =  Color.White) },
//            selected = (selectedIndex.value == 2),
//            onClick = {
//
//                navController.navigate(ROUTE_SEARCH) {
//                    popUpTo(ROUTE_HOME) { inclusive = true }
//                }
//
//            })
//
//    }
//}