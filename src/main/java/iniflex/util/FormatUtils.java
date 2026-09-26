package iniflex.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class FormatUtils {

    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final DecimalFormat FORMATO_VALOR =
            new DecimalFormat("#,##0.00",
                    new DecimalFormatSymbols(Locale.forLanguageTag("pt-BR")));

    private FormatUtils() {
    }

    public static String data(LocalDate data) {
        return data.format(FORMATO_DATA);
    }

    public static String valor(BigDecimal valor) {
        return FORMATO_VALOR.format(valor);
    }
}
