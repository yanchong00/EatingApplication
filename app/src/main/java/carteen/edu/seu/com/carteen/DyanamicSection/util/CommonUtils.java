package carteen.edu.seu.com.carteen.DyanamicSection.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import carteen.edu.seu.com.carteen.MyApplication;
import carteen.edu.seu.com.carteen.R;


public class CommonUtils {
	public static ImageLoader mImageLoader = ImageLoader.getInstance();
	public static CommonUtils util;
	public static InputMethodManager imm;

	public CommonUtils(Context context) {
		imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
	}

	public CommonUtils() {
	}

	public static CommonUtils getUtilInstance() {
		if (util == null) {

			util = new CommonUtils();
		}
		return util;
	}




	//加载网络图片
	public void displayNetworkImage(String url, ImageView view) {
		if (view == null) {
			return;
		}
		/*DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory(true)
				.cacheOnDisk(true).imageScaleType(ImageScaleType.IN_SAMPLE_INT)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.showImageOnLoading(R.drawable.image_show_default)
				.build();
		mImageLoader.displayImage(CommonUrl.imageUrl + url, view, options);*/
		Glide.with(MyApplication.getContext())
				.load(url).centerCrop()
				.placeholder(R.drawable.loading_error)
				.error(R.drawable.loading_error)
				.into(view);
	}





	public int getViewHeight(Context context, String str) {

		int height = 0;

		if (str.equals("videoView")) {
			height = MyApplication.screenWidth * 9 / 16;
		}
		if (str.equals("gameCollectPoster")) {
			height = MyApplication.screenWidth * 3 / 8;
		}
		if (str.equals("homePoster")) {
			height = MyApplication.screenWidth * 132 / 355;
		}
		return height;
	}

	public void getViewHeight(View view, View view2) {
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		int height = view.getMeasuredHeight();
		int width = view.getMeasuredWidth();
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, height);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		view2.setLayoutParams(params);
	}

	public int getPhoneAndroidSDK() {

		int version = 0;
		try {
			version = Integer.valueOf(android.os.Build.VERSION.SDK);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return version;
	}

	public String transformMillisToDate(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
		 Locale.CHINA);
//		SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm",
//				Locale.CHINA);
		return format.format(calendar.getTime());
	}

	public String msToMS(long ms) {
		Integer s = 1000;
		Integer m = s * 60;
		Long minute = ms / m;
		Long second = (ms - minute) / s;
		StringBuilder sb = new StringBuilder();
		if (minute > 0) {
			if (minute < 10) {
				sb.append("0" + minute + ":");
			} else {
				sb.append(minute + ":");
			}
		} else {
			sb.append("00:");
		}
		if (second > 0) {
			if (second < 10) {
				sb.append("0" + second);
			} else {
				sb.append(second);
			}
		} else {
			sb.append("00");
		}
		return sb.toString();
	}


	public void createSharedPreferences() {

	}

	public boolean isHanZi(Context context, String text) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(text);
		m = p.matcher(text);
		return m.matches();
	}

	public boolean isConnectingToInternet(Context context) {

		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (manager != null) {
			NetworkInfo[] info = manager.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++) {
					System.out.println(info[i].getState());
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
		}
		return false;
	}

	public boolean isWifiConnected(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo wifi = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		return wifi.isConnected();

	}

	public void hideIMM() {
		imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
				InputMethodManager.HIDE_NOT_ALWAYS);
	}

	public void showIMM() {
		imm.toggleSoftInput(0, InputMethodManager.RESULT_SHOWN);
	}

	public void deleteFile(Context context, File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				for (File files : file.listFiles()) {
					if (files.isFile()) {
						files.delete();
					} else if (files.isDirectory()) {
						deleteFile(context, files);
					}
				}
				file.delete();
			}

		}
	}

	private List<ResolveInfo> getShareApps(Context context) {
		List<ResolveInfo> mApps = new ArrayList<ResolveInfo>();
		Intent intent = new Intent(Intent.ACTION_SEND, null);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.setType("text/plain");
		PackageManager pManager = context.getPackageManager();
		mApps = pManager.queryIntentActivities(intent,
				PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
		return mApps;
	}


	public boolean isAppExit(Context context, String packageName) {
		if (packageName == null || "".equals(packageName))
			return false;
		try {
			ApplicationInfo info = context.getPackageManager()
					.getApplicationInfo(packageName,
							PackageManager.GET_META_DATA);
			return true;
		} catch (NameNotFoundException e) {
			return false;
		}
	}


	public int dp2px(Context context, int dp) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}

	public int px2dp(Context context, int px) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (px / scale + 0.5f);
	}

	public boolean isMobileNum(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		System.out.println(m.matches() + "---");
		return m.matches();

	}
	public static int getScreenWidth(Context context)
	{
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}

	/**
	 * 获得屏幕宽度
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context)
	{
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}
	public void showToast(Context context, String string) {
		Toast toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
		toast.show();
	}

	public void showLongToast(Context context, String string) {
		Toast toast = Toast.makeText(context, string, Toast.LENGTH_LONG);
		toast.show();
	}

	/**
	 　　* 从服务器取图片
	 　　*http://bbs.3gstdy.com
	 　　* @param url
	 　　* @return
	 　　*/
	public static Bitmap getHttpBitmap(String url) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {
			Log.d("getHttpBitmap", url);
			myFileUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setConnectTimeout(0);
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * 把图片裁成圆形图片
	 * @param bitmap
	 * @param ratio
	 * @return
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, float ratio) {
		System.out.println("图片是否变成圆形模式了+++++++++++++");
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawRoundRect(rectF, bitmap.getWidth() / ratio,
				bitmap.getHeight() / ratio, paint);

		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		System.out.println("pixels+++++++" + String.valueOf(ratio));

		return output;

	}
}