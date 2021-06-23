package com.example.fitbit;

import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;

interface AddReminderActivityI {
    // Obtain time from time picker
    void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute);
}
