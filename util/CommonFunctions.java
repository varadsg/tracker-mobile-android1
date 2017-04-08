package planar.weinvest.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import planar.weinvest.R;

public class CommonFunctions {

	public static Context ctx;
	public static TextView tv_sidemenu_profiename, tv_sidemenu_profiecity;

	public static boolean status = true;

	public static void changeactivity(Context context, String Act_des,
			boolean isfinish, boolean isSlideOutRight) {
		Intent i = new Intent();
		i.setClassName(context.getPackageName(), context.getPackageName() + "."
				+ Act_des);
		((Activity) context).startActivityForResult(i, 0);

		if (isfinish) {
			((Activity) context).finish();
		}

		if (isSlideOutRight) {
			((Activity) context).overridePendingTransition(
					R.anim.slide_in_right, R.anim.slide_out_left);
		} else {
			((Activity) context).overridePendingTransition(
					R.anim.slide_in_left, R.anim.slide_out_right);
		}
	}

	public static void finishActivity(Context context) {
		((Activity) context).finish();
		((Activity) context).overridePendingTransition(R.anim.slide_in_left,
				R.anim.slide_out_right);
	}

	public static void initShareIntent(Activity act) {
		try {
			Intent shareIntent = new Intent();
			shareIntent.setAction(Intent.ACTION_SEND);
			shareIntent.putExtra(Intent.EXTRA_SUBJECT, "WOOFR");
			shareIntent.putExtra(Intent.EXTRA_TEXT,
					"https://play.google.com/store/apps/details?id=com.woofr");
			shareIntent.setType("text/plain");
			shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			act.startActivity(Intent.createChooser(shareIntent, "Share"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressLint("NewApi")
	public static void exitAlert(final Activity activity) {
		try {
			AlertDialog.Builder builder = new AlertDialog.Builder(activity,
					AlertDialog.THEME_HOLO_LIGHT);
			builder.setTitle(activity.getResources().getString(
					R.string.app_name));
			builder.setMessage("Do you want to Exit?");

			builder.setPositiveButton("YES",
					new DialogInterface.OnClickListener() {
						@SuppressLint("InlinedApi")
						public void onClick(DialogInterface dialog, int which) {

							final Intent relaunch = new Intent(activity,
									Exiter.class)
									.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
											| Intent.FLAG_ACTIVITY_CLEAR_TASK
											| Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
							activity.startActivity(relaunch);
							activity.finish();
						}
					});

			builder.setNegativeButton("NO",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});

			AlertDialog dialog = builder.create();
			dialog.show();
		} catch (Exception e) {
		}
	}

	// TODO : Simpl
	@SuppressLint("NewApi")
	public static void showAlertDialog(final Context context, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context,
				AlertDialog.THEME_HOLO_LIGHT);
		builder.setTitle(context.getResources().getString(R.string.app_name));
		builder.setMessage(message);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public static int getDiffYears(Date first, Date last) {
		Calendar a = getCalendar(first);
		Calendar b = getCalendar(last);
		int diff = a.get(Calendar.YEAR) - b.get(Calendar.YEAR);

		return diff;
	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(date);
		return cal;
	}

	@SuppressLint("NewApi")
	public static boolean getresponse(Context context, String message) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context,
				AlertDialog.THEME_HOLO_LIGHT);

		builder.setTitle(context.getResources().getString(R.string.app_name));
		builder.setMessage(message);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				status = true;
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				status = false;
				dialog.dismiss();
			}

		});
		AlertDialog dialog = builder.create();
		dialog.show();
		return status;
	}

	/*public static Bitmap generateQRCode(String string) {
		Bitmap bitmap = null;
		QRCodeWriter writer = new QRCodeWriter();
	    try {
	        BitMatrix bitMatrix = writer.encode(string, BarcodeFormat.QR_CODE, 512, 512);
	        int width = bitMatrix.getWidth();
	        int height = bitMatrix.getHeight();
	        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
	        for (int x = 0; x < width; x++) {
	            for (int y = 0; y < height; y++) {
	                bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
	            }
	        }
//	        iv_invoice_qrcode.setImageBitmap(bmp);
	        bitmap = bmp;

	    } catch (WriterException e) {
	        e.printStackTrace();
	    }catch (NullPointerException e) {
	    	e.printStackTrace();
		}
		return bitmap;
	}*/
	
	 // TODO : Get random number
    public static int nDigitRandomNo(int digits) {
    	
        int max = (int) Math.pow(10, (digits)) + 1; //for digits =7, max will be 9999999
        int min = (int) Math.pow(10, digits - 1); //for digits = 7, min will be 1000000
        int range = max - min; //This is 8999999
        Random r = new Random();
        int x = r.nextInt(range);// This will generate random integers in range 0 - 8999999
        int nDigitRandomNo = x + min; //Our random rumber will be any random number x + min
        return nDigitRandomNo;
    }
}
