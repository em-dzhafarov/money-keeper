package com.dzhafarov.filters.ui

import android.content.Context
import com.dzhafarov.filters.presentation.FilterStringProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class FilterStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : FilterStringProvider {

    override val applyFilters: String
        get() = context.getString(R.string.filter_screen_apply_filters)

    override val clearFilters: String
        get() = context.getString(R.string.filter_screen_clear_filters)

    override val cancel: String
        get() = context.getString(R.string.filter_screen_cancel)

    override val priceRangeTitle: String
        get() = context.getString(R.string.filter_screen_price_range_title)

    override val descriptionTitle: String
        get() = context.getString(R.string.filter_screen_description_title)

    override val descriptionHint: String
        get() = context.getString(R.string.filter_screen_description_hint)

    override val currencyTitle: String
        get() = context.getString(R.string.filter_screen_currency_title)

    override val paymentMethodTitle: String
        get() = context.getString(R.string.filter_screen_payment_method_title)

    override val paymentReasonTitle: String
        get() = context.getString(R.string.filter_screen_payment_reason_title)
}
