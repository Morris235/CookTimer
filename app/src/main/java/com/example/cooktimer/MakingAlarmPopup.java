package com.example.cooktimer;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//어댑터 만들기 +리사이클러 상속
public class MakingAlarmPopup extends AppCompatActivity {
    //이 변수는 onActivityResult 에서 requestCode 로 반환되는 값입니다
    private static final int PICK_FROM_ALBUM = 1;
    //전역변수로 File 타입의 tempFile 을 선언해 주세요. 이 tempFile 에 받아온 이미지를 저장할거에요.
    private File tempFile;
    /*Intent 를 통해 카메라화면으로 이동할 수 있습니다.
    이때 startAcitivtyResult 에는 PICK_FROM_CAMER 를 파라미터로 넣어줍니다.*/
    private static final int PICK_FROM_CAMERA = 2;
    //카메라 퍼미션
    private final int MY_PERMISSIONS_REQUEST_CAMERA=1;
    //갤러리 퍼미션
    String[] permission_list = {
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_CONTACTS
    };
    //카메라 이미지 회전 적역변수
    private Boolean isCamera = false;

    //스피너 버튼 전달값 받기 연구중
    //어댑터에 던져줄 어레이리스트 데이터
    List<Data> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_making_alarm_popup);

        //완료버튼을 누를 때 마다 아이템이 하나씩 생겨야함


        // 1.알랍 팝업의 리사이클러뷰를 찾아 객체로 지정
        RecyclerView recyclerView = findViewById(R.id.recyclerView4);


        // 2.리사이클러뷰 어댑터 생성->리사이클러뷰에 보여줄 아이템과 아이템을 어떻게 보여줄지에 대한 레이아웃을 지정
        MakingAlarmPopup_Adapter adapter = new MakingAlarmPopup_Adapter(this,R.layout.activity_food_item_for_making,items);
        recyclerView.setAdapter(adapter);

        // 3.리사이클러뷰의 레이아웃 매니저를 지정하는 코드(리니어스타일)
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL); //아이템 방향 지정
        recyclerView.setLayoutManager(layoutManager); //setLayoutManager 메소드를 사용해서 매니저를 리사이클러뷰에 설정

        // 4.리사이클러뷰의 아이템이 추가되거나 삭제되는 등의 동작에 따른 애니메이션을 설정
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // 6.0 마쉬멜로우 이상일 경우에는 권한 체크 후 권한 요청
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.d("", "권한 설정 완료");
            } else {
                Log.d("", "권한 설정 요청");
                ActivityCompat.requestPermissions(MakingAlarmPopup.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }


        int permssionCheck = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA);

        if (permssionCheck!= PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this,"권한 승인이 필요합니다",Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                Toast.makeText(this,"사용을 위해 카메라 권한이 필요합니다.",Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);
                Toast.makeText(this,"사용을 위해 카메라 권한이 필요합니다.",Toast.LENGTH_LONG).show();
            }
        }

        //화면전환 버튼 -> 알람만들기 팝업(로그인)
        Button makingalarm = (Button) findViewById(R.id.alarmB);
        makingalarm.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(),MakingAlarmSpinner.class);
                        Log.i("태그","알람만들기 버튼 눌림");
                        startActivity(intent);
                    }
                });

        Button btnCamera = (Button) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("태그","카메라 버튼 눌림");
                takePhoto();
            }
        });

        Button btnGallery = (Button)findViewById(R.id.btnGallery);
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("태그","앨범 버튼 눌림");
                goToAlbum();
            }
        });
    }//onCreat 닫는 괄호

//    public List<Data> onButtonCount(int buttoncount){
//
//        items.add(buttoncount,data);
//        return items;
//    }

//    //5. 리사이클러뷰에서 보여줄 아이템을 생성하는 메소드
//    private List<Data> createItemList(int buttoncount){
//        List<String> items = new ArrayList<>();
//            items.add(buttoncount,data);
//        return items;
//    }

    private void goToAlbum() {
        isCamera =false;
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
        /*startActivityForResult 를 통해 다른 Activity 로 이동한 후 다시 돌아오게 되면 onActivityResult 가 동작되게 됩니다.
        이때 startActivityForResult 의 두번 째 파라미터로 보낸 값 {여기서는 PICK_FROM_ALBUM 이겠죠?}이
        requestCode 로 반환되는 동작을 합니다.*/
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //예외사항 처리 =앨범화면으로 이동 했지만 선택을 하지 않고 뒤로 간 경우 또는 카메라로 촬영한 후 저장하지 않고 뒤로 가기를 간 경우
        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();
            if(tempFile != null) {
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        Log.e("예외사항", tempFile.getAbsolutePath() + " 삭제 성공");
                        tempFile = null;
                    }
                }
            }
            return;
        }
        if (requestCode == PICK_FROM_ALBUM) {

            Uri photoUri = data.getData();

            Cursor cursor = null;

            try {
                /*
                 *  Uri 스키마를
                 *  content:/// 에서 file:/// 로  변경한다.
                 */
                String[] proj = {MediaStore.Images.Media.DATA};

                assert photoUri != null;
                cursor = getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));

            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }

            setImage();
            //onActivityResult 분기 처리
            //onActivityResult 에서 requestCode 를 앨범에서 온 경우와 카메라에서 온 경우로 나눠서 처리해줍니다.
        }else if (requestCode == PICK_FROM_CAMERA) {
            setImage();
        }
    }
    //갤러리에서 받아온 이미지 넣기
    private void setImage() {

        ImageView imageView = findViewById(R.id.imageView4);
        /*첫 번째 파라미터: 변형시킬 tempFile 을 넣었습니다.
         두 번째 파라미터에는 변형시킨 파일을 다시 tempFile에 저장.
         세 번째 파라미터는 이미지의 긴 부분을 1280 사이즈로 리사이징 하라는 의미.
         네 번째 파라미터를 통해 카메라에서 가져온 이미지인 경우 카메라의 회전각도를 적용해 줍니다.(앨범에서 가져온 경우에는 회전각도를 적용 시킬 필요가 없겠죠?)*/

        //이미지 회전 인스턴스
        ImageResizeUtils.resizeFile(tempFile,tempFile,1280,isCamera);

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);

        imageView.setImageBitmap(originalBm);

    }
    private void takePhoto() {

        isCamera=true;

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            tempFile = createImageFile();
        } catch (IOException e) {
            Toast.makeText(this, "이미지 처리 오류! 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            finish();
            e.printStackTrace();
        }
        if (tempFile != null) {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

                Uri photoUri = FileProvider.getUriForFile(this,
                        "com.example.cooktimer.provider", tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, PICK_FROM_CAMERA);

            } else {

                Uri photoUri = Uri.fromFile(tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, PICK_FROM_CAMERA);

            }
        }
    }
    //카메라에서 찍은 사진을 저장할 파일 만들기
    private File createImageFile() throws IOException {

        // 이미지 파일 이름 ( blackJin_{시간}_ )
        String timeStamp = new SimpleDateFormat("HHmmss").format(new Date());
        String imageFileName = "cooktimer" + timeStamp + "_";

        // 이미지가 저장될 폴더 이름 ( blackJin )
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/cooktimer/");
        if (!storageDir.exists()) storageDir.mkdirs();

        // 빈 파일 생성
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        return image;
    }
    // 권한 요청
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("", "onRequestPermissionsResult");
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            Log.d("", "Permission: " + permissions[0] + "was " + grantResults[0]);
        }
    }

}
