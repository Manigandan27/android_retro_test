package com.marwaeltayeb.souq.view;

import static com.marwaeltayeb.souq.storage.LanguageUtils.loadLocale;
import static com.marwaeltayeb.souq.utils.Constant.CAMERA_PERMISSION_CODE;
import static com.marwaeltayeb.souq.utils.Constant.CATEGORY;
import static com.marwaeltayeb.souq.utils.Constant.LOCALHOST;
import static com.marwaeltayeb.souq.utils.Constant.PRODUCT;
import static com.marwaeltayeb.souq.utils.Constant.READ_EXTERNAL_STORAGE_CODE;
import static com.marwaeltayeb.souq.utils.ImageUtils.getImageUri;
import static com.marwaeltayeb.souq.utils.ImageUtils.getRealPathFromURI;
import static com.marwaeltayeb.souq.utils.InternetUtils.isNetworkConnected;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
//import com.marwaeltayeb.souq.LanguageDetect;
import com.marwaeltayeb.souq.R;

import com.marwaeltayeb.souq.bottomnavigation.Bnav_Account;
import com.marwaeltayeb.souq.bottomnavigation.Bnav_Profile;
import com.marwaeltayeb.souq.databinding.ActivityProductBinding;

import com.marwaeltayeb.souq.receiver.NetworkChangeReceiver;
import com.marwaeltayeb.souq.recycle.APIService2;
import com.marwaeltayeb.souq.recycle.Banners;
import com.marwaeltayeb.souq.recycle.Banners2;
import com.marwaeltayeb.souq.recycle.DashboardAdapter;
import com.marwaeltayeb.souq.recycle.FlipperAdapter2;

import com.marwaeltayeb.souq.retro_items.Apiclient;
import com.marwaeltayeb.souq.retro_items.D_Api;
import com.marwaeltayeb.souq.retro_items.Dynamic_model;
import com.marwaeltayeb.souq.retro_items.dynamic_data.Adapter_dynamicitems;
import com.marwaeltayeb.souq.retro_items.dynamic_data.DashboardProductList;
import com.marwaeltayeb.souq.retro_items.dynamic_data.Datum;


import com.marwaeltayeb.souq.recycle.ToDoModel;
import com.marwaeltayeb.souq.retro_items.Adapter_Slider;
import com.marwaeltayeb.souq.retro_items.Adapter_menu_dash;
import com.marwaeltayeb.souq.retro_items.Adapter_product;
import com.marwaeltayeb.souq.retro_items.Adapter_retrodash;
import com.marwaeltayeb.souq.retro_items.Dash_menu_model;
import com.marwaeltayeb.souq.retro_items.Retro_model_class;
import com.marwaeltayeb.souq.retro_items.Dashboard_model_1;
import com.marwaeltayeb.souq.retro_items.Product_model_1;
import com.marwaeltayeb.souq.retro_items.RetrofitInstance;
import com.marwaeltayeb.souq.retro_items.SliderModelClass;
import com.marwaeltayeb.souq.storage.LoginUtils;
import com.marwaeltayeb.souq.utils.FlagsManager;
import com.marwaeltayeb.souq.utils.OnNetworkListener;


import com.marwaeltayeb.souq.utils.Slide;
import com.marwaeltayeb.souq.viewmodel.UploadPhotoViewModel;
import com.marwaeltayeb.souq.viewmodel.UserImageViewModel;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener, OnNetworkListener,
        NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "ProductActivity";
    private ActivityProductBinding binding;

//    private ProductAdapter mobileAdapter;
//    private ProductAdapter laptopAdapter;
//    private ProductAdapter historyAdapter;

//    private ProductViewModel productViewModel;
//    private HistoryViewModel historyViewModel;

    private UploadPhotoViewModel uploadPhotoViewModel;
    private UserImageViewModel userImageViewModel;

    private DashboardAdapter dashboardAdapter;

    private Snackbar snack;

    private CircleImageView circleImageView;
    private NetworkChangeReceiver mNetworkReceiver;


    private List<ToDoModel> itemList = new ArrayList<>();
    private RecyclerView recyclerview;
    private DashboardAdapter mAdapter;//dashboardAdapter

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

//    CarouselView carouselView;
//    int NUMBER_OF_PAGES = 5;


//    List<SliderModelClass> sliderItemList = new ArrayList<>();
//    SliderModelClass sliderItem = new SliderModelClass();

    public static final String BASE_URL2 = "https://run.mocky.io/v3/d8acaf5c-2d83-43a4-850e-c9068db0bb8b/";

    private AdapterViewFlipper adapterViewFlipper2;
    private SliderView Sadapter;

//   SliderAnimations


    RecyclerView dashRecycle, productRecycle, dashMenuRecycle, dynamicRecycle;
    RecyclerView  dynamicRecycle2,dynamicRecycle3,dynamicRecycle4,dynamicrecycle;


    ImageView ivImage;
    TextView tvName;
    TextView btLogout;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient googleSignInClient;

    ImageView imageView;
    TextView textName, textEmail;
    FirebaseAuth mAuth;

    Button signinotp;

    Button logoutBtn;
    TextView userName, userEmail, userId;
    ImageView profileImage;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;

    BottomNavigationView bottomNavigationView;

    Adapter_Slider adapter1;

    ArrayList<DashboardProductList> dashProductLists;

    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product);
        int userID = LoginUtils.getInstance(this).getUserInfo().getId();

//        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
//        productViewModel.loadMobiles("mobile", userID);
//        productViewModel.loadLaptops("laptop", userID);

//        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
//        historyViewModel.loadHistory(userID);

        uploadPhotoViewModel = ViewModelProviders.of(this).get(UploadPhotoViewModel.class);
        userImageViewModel = ViewModelProviders.of(this).get(UserImageViewModel.class);

        snack = Snackbar.make(findViewById(android.R.id.content), getResources().getString(R.string.no_internet_connection), Snackbar.LENGTH_INDEFINITE);

        binding.included.content.txtSeeAllMobiles.setOnClickListener(this);
        binding.included.content.txtSeeAllLaptops.setOnClickListener(this);
        binding.included.content.txtCash.setOnClickListener(this);
        binding.included.content.txtReturn.setOnClickListener(this);

        binding.included.txtSearch.setOnClickListener(this);

        setUpViews();

//        getMobiles();
//        getLaptops();
//        getHistory();

        getUserImage();


//        flipImages(Slide.getSlides());
        mNetworkReceiver = new NetworkChangeReceiver();
        mNetworkReceiver.setOnNetworkListener(this);

        recyclerview = findViewById(R.id.listOfMobiles);
        mAdapter = new DashboardAdapter(itemList);
        RecyclerView.LayoutManager mLayoutManger = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(mLayoutManger);
        recyclerview.setAdapter(mAdapter);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        prepareItem();

//        ArrayList<Model_dashboard> image = initCities();//

        this.recyclerView = (RecyclerView) findViewById(R.id.listOfMobiles);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        this.recyclerView.setLayoutManager(mLayoutManager);

//        adapter = new Adapter_dashboard(image);//
        this.recyclerView.setAdapter(adapter);

        adapterViewFlipper2 = findViewById(R.id.imageSlider);
        Sadapter = findViewById(R.id.imageSlider3);


        productRecycle = findViewById(R.id.recycle_products);
        dashRecycle = findViewById(R.id.recycler_dash_items);

        sliderImages();
        fetchDashboardList();
        fetchProductList();
        fetchDashMenuList();

        //static images
//        fetchDynamicList();
//        fetchDynamicList();
//        fetchDynamicList2();
//        fetchDynamicList3();
//        fetchDynamicList4();

        testDynamicdata();
//-----------------------------------google signin details-------------------------------------


        btLogout = findViewById(R.id.bt_logout);
        ivImage = findViewById(R.id.iv_image);
        tvName = findViewById(R.id.tv_name);


        logoutBtn = findViewById(R.id.logoutBtn1);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                if (status.isSuccess()) {
                                    gotoMainActivity();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Session not close", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });



        Gson gson=new Gson();
        renewItems();


// bottom navigation


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NotNull MenuItem item) {
                switch (item.getItemId()) {

// bottom navigation  user profile page

                    case R.id.btm_nav_myProfile:
                        // Handle home navigation item

                        Intent i = new Intent(ProductActivity.this, Bnav_Account.class);
                        startActivity(i);
                        Toast.makeText(ProductActivity.this, "Profile item selected", Toast.LENGTH_SHORT).show();

                        break;

                    case R.id.btm_nav_home:
                        Toast.makeText(ProductActivity.this, "Home item selected", Toast.LENGTH_SHORT).show();

                        break;

                    case R.id.btm_nav_notification:
                        Toast.makeText(ProductActivity.this, "Notify item selected", Toast.LENGTH_SHORT).show();

                        break;

                    case R.id.btm_nav_catergory:
                        Toast.makeText(ProductActivity.this, "Category item selected", Toast.LENGTH_SHORT).show();

                        break;

                    case R.id.btm_nav_cart:

//                        Intent i1 = new Intent(ProductActivity.this, LanguageDetect.class);
//                        startActivity(i1);

                        Toast.makeText(ProductActivity.this, "Cart item selected", Toast.LENGTH_SHORT).show();


                        break;


                }
                return true;
            }
        });


    }

    private void testDynamicdata() {

        D_Api apiService = Apiclient.getClient().create(D_Api.class);
        apiService.getDynamicdata().enqueue(new Callback<Retro_model_class>() {

            @Override
            public void onResponse(Call<Retro_model_class> call, Response<Retro_model_class> response) {

                ArrayList<Datum> RPimages = response.body().getData();

//--------------------------------------------------

                dashRecycle = findViewById(R.id.recycle_dynamic_items);
//                GridLayoutManager DLayoutManager1=new GridLayoutManager(getApplicationContext(), 2);
                RecyclerView.LayoutManager DLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                dashRecycle.setLayoutManager(DLayoutManager);
//                dashRecycle.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.HORIZONTAL));

                Adapter_dynamicitems Padapter = new Adapter_dynamicitems(RPimages);
                dashRecycle.setAdapter(Padapter);

                if(RPimages!=null) {

                    Gson gson = new Gson();
                    String json = gson.toJson(RPimages);
                    Log.d(TAG, " bbbbbb : " + json);

                }

                Log.d(TAG, "Data_ApiModel received for verification nnnnn : "+ response);

            }


            @Override
            public void onFailure(Call<Retro_model_class> call, Throwable t) {
                Log.d("aaaaa", "error loading aaaaa API");
            }

        });

    }

    private void sliderImages() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL2).addConverterFactory(GsonConverterFactory.create()).build();
        APIService2 service = retrofit.create(APIService2.class);
        Call<Banners2> call =  service.getBanners();

        call.enqueue(new Callback<Banners2>() {
            //            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onResponse(Call<Banners2> call, Response<Banners2> response) {
                ArrayList<Banners>banners=response.body().getBanners();

                FlipperAdapter2 adapter = new FlipperAdapter2(getApplicationContext(), banners);
                Log.d("aaaaa", String.valueOf(banners));
                Log.d(TAG, " rrrrrrr : "+ response);

                adapterViewFlipper2.setAdapter(adapter);
                adapterViewFlipper2.setFlipInterval(2000);
                adapterViewFlipper2.startFlipping();



            }

            @Override
            public void onFailure(Call<Banners2> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    //------------------------renewitems(slide images)-----------------------------

    public void renewItems() {

        Log.d(TAG, "renewitems aaaaaaa :");
        Call<Retro_model_class> calls = RetrofitInstance.getInstanceBanner().getBannerApi().getBannerSlide(); //retroinstance
        calls.enqueue(new Callback<Retro_model_class>() {
            @Override
            public void onResponse(Call<Retro_model_class> calls, Response<Retro_model_class> response) {

                Log.d(TAG, "renewitems aaaaaaa  response : ");

                ArrayList<SliderModelClass> images = response.body().getBanner();
                Adapter_Slider adapter1 = new Adapter_Slider(getApplicationContext(), images);

                SliderModelClass sliderItem = new SliderModelClass();

                Sadapter = findViewById(R.id.imageSlider3);
                Sadapter.setSliderAdapter(adapter1);


                Sadapter.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                Sadapter.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                Sadapter.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                Sadapter.setIndicatorUnselectedColor(Color.GRAY);
                Sadapter.setScrollTimeInSec(3);
                Sadapter.setAutoCycle(true);
                Sadapter.startAutoCycle();

            }

            @Override
            public void onFailure(Call<Retro_model_class> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


//        List<SliderModelClass> sliderItemList = new ArrayList<>();
//        //dummy data
//
//        SliderModelClass sliderItem = new SliderModelClass();
//
//        sliderItem.setUrl(".");
//
//        sliderItemList.add(sliderItem);
//        adapter1.renewItems(sliderItemList);
//    }




    //-------------api dash menu--------

    private void fetchDashMenuList() {

        Call<Retro_model_class> call = RetrofitInstance.getInstancemenu().getMyApimenu().getMenuDash(); //retroinstance

        call.enqueue(new Callback<Retro_model_class>() {
            @Override
            public void onResponse(Call<Retro_model_class> call, Response<Retro_model_class> response) {

                ArrayList<Dash_menu_model> RPimages1 = response.body().getDmenuProduct();//Dash_menu_model//getDmenuProduct

                Dash_menu_model dash_menu_model=new Dash_menu_model("","","","");


                    int statusCode = response.code();
                    String res = response.body().toString();

                    Log.d(TAG, " 123456 : " + statusCode);
                    Log.d("111111", (res));



                dashMenuRecycle = findViewById(R.id.recycler_dash_menuitems);
//                RecyclerView.LayoutManager DLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, true);

                GridLayoutManager DLayoutManager=new GridLayoutManager(getApplicationContext(), 4);
                dashMenuRecycle.setLayoutManager(DLayoutManager);

                recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.HORIZONTAL));

                Adapter_menu_dash Padapter1 = new Adapter_menu_dash(RPimages1);//Adapter_menu_dash
                dashMenuRecycle.setAdapter(Padapter1);

                Log.d(TAG, "Data_ApiModel received for verification nnnnn : ");
            }

            @Override
            public void onFailure(Call<Retro_model_class> call, Throwable t) {
                Log.e(TAG, "Error Message: " + t.getLocalizedMessage());
            }
        });
    }


    private void fetchProductList() {

        Call<Retro_model_class> call = RetrofitInstance.getInstance().getMyApi().getProduct();
        call.enqueue(new Callback<Retro_model_class>() {
            @Override
            public void onResponse(Call<Retro_model_class> call, Response<Retro_model_class> response) {

                ArrayList<Product_model_1> RPimages = response.body().getdProduct();

                dashRecycle = findViewById(R.id.recycle_products);
                RecyclerView.LayoutManager DLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                dashRecycle.setLayoutManager(DLayoutManager);
//                GridLayoutManager DLayoutManager=new GridLayoutManager(getApplicationContext(), 2);
//                dashRecycle.setLayoutManager(DLayoutManager);

//      //dashRecycle.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.HORIZONTAL));

                Adapter_product Padapter = new Adapter_product(RPimages);
                dashRecycle.setAdapter(Padapter);

                Log.d(TAG, "Data_ApiModel received for verification nnnnn : ");
            }

            @Override
            public void onFailure(Call<Retro_model_class> call, Throwable t) {
                Log.e(TAG, "Error Message: " + t.getLocalizedMessage());
            }
        });


    }

    private void fetchDashboardList() {

        Call<Retro_model_class> call = RetrofitInstance.getInstance().getMyApi().getDash();

        call.enqueue(new Callback<Retro_model_class>() {
            @Override
            public void onResponse(Call<Retro_model_class> call, Response<Retro_model_class> response) {

                ArrayList<Dashboard_model_1> RDimages = response.body().getDboard();

                dashRecycle = findViewById(R.id.recycler_dash_items);
                RecyclerView.LayoutManager DLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                dashRecycle.setLayoutManager(DLayoutManager);

                Adapter_retrodash Dadapter = new Adapter_retrodash(RDimages);
                dashRecycle.setAdapter(Dadapter);

//                Log.d(TAG, "Data_ApiModel received for verification nnnnn : ");
            }

            @Override
            public void onFailure(Call<Retro_model_class> call, Throwable t) {

                Log.e(TAG, "Error Message: " + t.getLocalizedMessage());
            }
        });
    }




    private void prepareItem() {

//        ToDoModel model= new ToDoModel(R.drawable.slide);
//        itemList .add(model);
        ArrayList<ToDoModel> data= new ArrayList<>();
        data.add(new ToDoModel( R.drawable.slide));

        mAdapter.notifyDataSetChanged();
        recyclerview.setAdapter(mAdapter);
    }

    private void setUpViews() {
        Toolbar toolbar = binding.included.toolbar;
        setSupportActionBar(toolbar);

        DrawerLayout drawer = binding.drawerLayout;

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(this);

        View headerContainer = binding.navView.getHeaderView(0);
        circleImageView = headerContainer.findViewById(R.id.profile_image);
        circleImageView.setOnClickListener(this);
        TextView userName = headerContainer.findViewById(R.id.nameOfUser);
//        userName.setText(LoginUtils.getInstance(this).getUserInfo().getName());
//        TextView userEmail = headerContainer.findViewById(R.id.emailOfUser);
//        userEmail.setText(LoginUtils.getInstance(this).getUserInfo().getEmail());

        binding.included.content.listOfMobiles.setHasFixedSize(true);
        binding.included.content.listOfMobiles.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.included.content.listOfMobiles.setItemAnimator(null);

        binding.included.content.listOfLaptops.setHasFixedSize(true);
        binding.included.content.listOfLaptops.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.included.content.listOfLaptops.setItemAnimator(null);

        binding.included.content.historyList.setHasFixedSize(true);
        binding.included.content.historyList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.included.content.historyList.setItemAnimator(null);

//        mobileAdapter = new ProductAdapter(this, this);
//        laptopAdapter = new ProductAdapter(this, this);
//        historyAdapter = new ProductAdapter(this, this);

        if (FlagsManager.getInstance().isHistoryDeleted()) {
            binding.included.content.textViewHistory.setVisibility(View.GONE);
        }
    }


//    private void getMobiles() {
//        if (isNetworkConnected(this)) {
//            productViewModel.productPagedList.observe(this, products -> mobileAdapter.submitList(products));
//            binding.included.content.listOfMobiles.setAdapter(mobileAdapter);
//        } else {
//            showOrHideViews(View.INVISIBLE);
//            showSnackBar();
//        }
//    }


//    private void getLaptops() {
//        if (isNetworkConnected(this)) {
//            productViewModel.laptopPagedList.observe(this, products -> laptopAdapter.submitList(products));
//            binding.included.content.listOfLaptops.setAdapter(laptopAdapter);
//        } else {
//            showOrHideViews(View.INVISIBLE);
//            showSnackBar();
//        }
//    }

//    private void getHistory() {
//        if (isNetworkConnected(this)) {
//            historyViewModel.historyPagedList.observe(this, products -> {
//                binding.included.content.historyList.setAdapter(historyAdapter);
//                historyAdapter.submitList(products);
//                historyAdapter.notifyDataSetChanged();
//
//                products.addWeakCallback(null, productCallback);
//            });
//        } else {
//            showOrHideViews(View.INVISIBLE);
//            binding.included.content.textViewHistory.setVisibility(View.GONE);
//            showSnackBar();
//        }

//    }

  /*
  private void flipImages(List<Integer> images) {
        for (int image : images) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(image);
//            binding.included.content.imageSlider.addView(imageView);
        }

        binding.included.content.imageSlider.setFlipInterval(2000);
        binding.included.content.imageSlider.setAutoStart(true);

        // Set the animation for the view that enters the screen
        binding.included.content.imageSlider.setInAnimation(this, R.anim.slide_in_right);
        // Set the animation for the view leaving th screen
        binding.included.content.imageSlider.setOutAnimation(this, R.anim.slide_out_left);
    }
    */

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.txtSeeAllMobiles:
//                Intent mobileIntent = new Intent(this, AllMobilesActivity.class);
//                startActivity(mobileIntent);
//                break;
//            case R.id.txtSeeAllLaptops:
////                Intent laptopIntent = new Intent(this, AllLaptopsActivity.class);
////                startActivity(laptopIntent);
//                break;
            case R.id.profile_image:
                showCustomAlertDialog();
                break;
            case R.id.txtCash:
                showNormalAlertDialog(getString(R.string.cash));
                break;
            case R.id.txtReturn:
                showNormalAlertDialog(getString(R.string.returnProduct));
                break;
//         //   case R.id.txtSearch:
//        //        Intent searchIntent = new Intent(ProductActivity.this, SearchActivity.class);
//                startActivity(searchIntent);
//                break;
            default: // Should not get here
        }
    }

    private void showNormalAlertDialog(String message) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null).show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.darkGreen));
    }

    private void showCustomAlertDialog() {
        final Dialog dialog = new Dialog(ProductActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_image_dialog);

        Button takePicture = dialog.findViewById(R.id.takePicture);
        Button useGallery = dialog.findViewById(R.id.useGallery);

        takePicture.setEnabled(true);
        useGallery.setEnabled(true);

        takePicture.setOnClickListener(v -> {
            launchCamera();
            dialog.cancel();
        });

        useGallery.setOnClickListener(v -> {
            getImageFromGallery();
            dialog.cancel();
        });

        dialog.show();
    }

    private void getImageFromGallery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_CODE);
            } else {
                try {
                    Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    getIntent.setType("image/*");

                    Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
                    getImageFromGallery.launch(chooserIntent);
                } catch (Exception exp) {
                    Log.i("Error", exp.toString());
                }
            }
        }
    }

    private void launchCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_PERMISSION_CODE);
            } else {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                getImageFromCamera.launch(cameraIntent);
            }
        }
    }

    ActivityResultLauncher<Intent> getImageFromGallery = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Uri imageUri = data.getData();
                    circleImageView.setImageURI(imageUri);

                    String filePath = getRealPathFromURI(this, imageUri);
                    Log.d(TAG, "getImageFromGallery: " + filePath);

                    uploadPhoto(filePath);
                }
            });

    ActivityResultLauncher<Intent> getImageFromCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    circleImageView.setImageBitmap(photo);

                    Uri imageUri = getImageUri(this, photo);
                    String filePath = getRealPathFromURI(this, imageUri);
                    Log.d(TAG, "getImageFromCamera: " + filePath);

                    uploadPhoto(filePath);
                }
            });


    private void uploadPhoto(String pathname) {
        uploadPhotoViewModel.uploadPhoto(pathname).observe(this, responseBody -> {
            Log.d(TAG, "Image Uploaded");
            getUserImage();
        });
    }

    private void getUserImage() {
        userImageViewModel.getUserImage(LoginUtils.getInstance(this).getUserInfo().getId()).observe(this, response -> {
            Log.d(TAG,  "getUserImage");

            if (response != null) {
                String imageUrl = LOCALHOST + response.getImagePath().replaceAll("\\\\", "/");

                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.profile_picture)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .priority(Priority.HIGH)
                        .dontAnimate()
                        .dontTransform();

                Glide.with(getApplicationContext())
                        .load(imageUrl)
                        .apply(options)
                        .into(circleImageView);
            }
        });


        signinotp=findViewById(R.id.loginpg);
        signinotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ProductActivity.this, LoginActivity.class));
            }
        });


    }

    public void showSnackBar() {
        snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        snack.show();
    }

    public void hideSnackBar() {
        snack.dismiss();
    }

    private void registerNetwork() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerNetwork();
    }

        /*
           if (authStateListener != null){
            FirebaseAuth.getInstance().signOut();
        }
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }
          */
    //

//        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
//        if (opr.isDone()) {
//            GoogleSignInResult result = opr.get();
//            handleSignInResult(result);
//        } else {
//            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
//                @Override
//                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
//                    handleSignInResult(googleSignInResult);
//                }
//            });
//        }
//
//        if (mAuth.getCurrentUser() == null) {
//            finish();
//            startActivity(new Intent(this, LoginActivity.class));
//        }
//    }

    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            userName.setText(account.getDisplayName());
            userEmail.setText(account.getEmail());
            userId.setText(account.getId());
            try {
                Glide.with(this).load(account.getPhotoUrl()).into(profileImage);
            } catch (NullPointerException e) {
                Toast.makeText(getApplicationContext(), "image not found", Toast.LENGTH_LONG).show();
            }

        } else {
            gotoMainActivity();
        }
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
//        Intent intent = new Intent(this, ProductActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mNetworkReceiver);
    }



    @Override
    public void onNetworkConnected() {
        hideSnackBar();
        showOrHideViews(View.VISIBLE);
//        getMobiles();
//        getLaptops();
//        getHistory();
        Log.d(TAG, "onNetworkConnected");
        getUserImage();
    }

    @Override
    public void onNetworkDisconnected() {
        showSnackBar();
    }

//    @Override
//    public void onClick(Product product) {
//        Intent intent = new Intent(ProductActivity.this, DetailsActivity.class);
//        // Pass an object of product class
//        intent.putExtra(PRODUCT, (product));
//        startActivity(intent);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        MenuItem addMenu = menu.findItem(R.id.action_addProduct);
        boolean isAdmin = LoginUtils.getInstance(this).getUserInfo().isAdmin();
        addMenu.setVisible(isAdmin);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                Toast.makeText(getApplicationContext(), "cart ", Toast.LENGTH_LONG).show();
////                Intent cartIntent = new Intent(this, CartActivity.class);
//                startActivity(cartIntent);
                return true;
            case R.id.action_addProduct:
//           oductInten     Intent addProductIntent = new Intent(this, AddProductActivity.class);
//                startActivity(addPrt);
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    private void showOrHideViews(int view) {
        binding.included.content.textViewMobiles.setVisibility(View.INVISIBLE);
        binding.included.content.txtSeeAllMobiles.setVisibility(View.INVISIBLE);
        binding.included.content.textViewLaptops.setVisibility(View.INVISIBLE);
        binding.included.content.txtSeeAllLaptops.setVisibility(View.INVISIBLE);
        binding.included.content.txtCash.setVisibility(View.INVISIBLE);
        binding.included.content.txtReturn.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        int id = menuItem.getItemId();


           switch (menuItem.getItemId()) {


                    case R.id.nav_myAccount:
                        // Handle home navigation item
                        Intent accountIntent = new Intent(this, AccountActivity.class);
                        startActivity(accountIntent);
                        break;

                    case R.id.nav_notification:
                        Toast.makeText(ProductActivity.this, " item selected", Toast.LENGTH_SHORT).show();

                        break;

                    case R.id.nav_mobiles:
                        Toast.makeText(ProductActivity.this, " item selected", Toast.LENGTH_SHORT).show();

                        break;

                    case R.id.nav_newsFeed:
                        Toast.makeText(ProductActivity.this, " item selected", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_wishList:
                        Toast.makeText(ProductActivity.this, " item selected", Toast.LENGTH_SHORT).show();
                        break;
//nav_trackOrder, nav_babies, nav_laptops, nav_mobiles
                }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

   }

//    private void goToCategoryActivity(String category) {
//        Intent categoryIntent = new Intent(this, CategoryActivity.class);
//        categoryIntent.putExtra(CATEGORY, category);
//        startActivity(categoryIntent);
//    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "logout ttttttttt: ");
        super.onBackPressed();
        this.finish();

//        onDestroy();
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            closeApplication();
//        }


    }

    private void closeApplication() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage(R.string.want_to_exit)
                .setPositiveButton(R.string.ok, (dialog, which) -> finish())
                .setNegativeButton(R.string.cancel, null)
                .show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this,R.color.darkGreen));
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this, R.color.darkGreen));
    }


    @Override
    protected void onResume() {
        super.onResume();
//        productViewModel.invalidate();
//        getMobiles();
//        getLaptops();
//        historyViewModel.invalidate();
//        getHistory();
    }

//    private final PagedList.Callback productCallback = new PagedList.Callback() {
//        @Override
//        public void onChanged(int position, int count) {
//            Log.d(TAG, "onChanged: "+ count);
//        }
//
//        @Override
//        public void onInserted(int position, int count) {
//            Log.d(TAG, "onInserted: "+ count);
//            if (count != 0) {
//                binding.included.content.textViewHistory.setVisibility(View.VISIBLE);
//                historyAdapter.notifyOnInsertedItem(position);
////                getHistory();
//            }
//        }
//
//        @Override
//        public void onRemoved(int position, int count) {
//            Log.d(TAG, "onRemoved: "+ count);
//        }
//    };
//
//    @Override
//    public void onClick(Product product) {
//
//    }
}
