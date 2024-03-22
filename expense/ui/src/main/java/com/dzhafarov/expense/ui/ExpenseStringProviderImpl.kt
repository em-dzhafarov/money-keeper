package com.dzhafarov.expense.ui

import android.content.Context
import com.dzhafarov.expense.presentation.ExpenseStringProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ExpenseStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ExpenseStringProvider {

    override fun title(isEdit: Boolean): String {
        return context.getString(
            if (isEdit) {
                R.string.edit_expense_screen
            } else {
                R.string.add_expense_screen
            }
        )
    }

    override val paymentReasonTitle: String
        get() = context.getString(R.string.expense_screen_payment_reason_title)

    override val paymentReasonError: String
        get() = context.getString(R.string.expense_screen_reason_error)

    override val paymentMethodTitle: String
        get() = context.getString(R.string.expense_screen_payment_method_title)

    override val paymentMethodError: String
        get() = context.getString(R.string.expense_screen_method_error)

    override val amountError: String
        get() = context.getString(R.string.expense_screen_amount_error)

    override val amountLabel: String
        get() = context.getString(R.string.expense_screen_amount_label)

    override val amountTitle: String
        get() = context.getString(R.string.expense_screen_amount_title)

    override fun saveTitle(isEdit: Boolean): String {
        return context.getString(
            if (isEdit) {
                R.string.expense_screen_update
            } else {
                R.string.expense_screen_save
            }
        )
    }

    override val deleteTitle: String
        get() = context.getString(R.string.expense_screen_delete)

    override val dateTimeTitle: String
        get() = context.getString(R.string.expense_screen_date_time_title)

    override val descriptionTitle: String
        get() = context.getString(R.string.expense_screen_description_title)

    override val descriptionLabel: String
        get() = context.getString(R.string.expense_screen_description_label)

    override fun plnCurrencyName(isFull: Boolean): String {
        return if (isFull) {
            context.getString(R.string.currency_pln_full)
        } else {
            context.getString(R.string.currency_pln)
        }
    }

    override fun usdCurrencyName(isFull: Boolean): String {
        return if (isFull) {
            context.getString(R.string.currency_usd_full)
        } else {
            context.getString(R.string.currency_usd)
        }
    }

    override fun euroCurrencyName(isFull: Boolean): String {
        return if (isFull) {
            context.getString(R.string.currency_eur_full)
        } else {
            context.getString(R.string.currency_eur)
        }
    }

    override fun todayAtTime(time: String): String {
        return context.getString(R.string.label_today) + " " +
                context.getString(R.string.label_at_time, time)
    }

    override fun yesterdayAtTime(time: String): String {
        return context.getString(R.string.label_yesterday) + " " +
                context.getString(R.string.label_at_time, time)
    }

    override fun atDateAndAtTime(date: String, time: String): String {
        return date + " " + context.getString(R.string.label_at_time, time)
    }

    override val paymentMethodCash: String
        get() = context.getString(R.string.payment_method_cash)

    override val paymentMethodCreditCard: String
        get() = context.getString(R.string.payment_method_credit_card)

    override val paymentMethodOnline: String
        get() = context.getString(R.string.payment_method_online)

    override val paymentReasonFood: String
        get() = context.getString(R.string.payment_reason_food)

    override val paymentReasonStudy: String
        get() = context.getString(R.string.payment_reason_study)

    override val paymentReasonTax: String
        get() = context.getString(R.string.payment_reason_tax)

    override val paymentReasonBeauty: String
        get() = context.getString(R.string.payment_reason_beauty)

    override val paymentReasonAccommodation: String
        get() = context.getString(R.string.payment_reason_accommodation)

    override val paymentReasonEntertainment: String
        get() = context.getString(R.string.payment_reason_entertainment)

    override val paymentReasonSubscription: String
        get() = context.getString(R.string.payment_reason_subscription)

    override val paymentReasonClothes: String
        get() = context.getString(R.string.payment_reason_clothes)

    override val paymentReasonTransportation: String
        get() = context.getString(R.string.payment_reason_transportation)

    override val paymentReasonTravel: String
        get() = context.getString(R.string.payment_reason_travel)

    override val paymentReasonHealth: String
        get() = context.getString(R.string.payment_reason_health)
}