# Generated by Django 4.2.2 on 2023-06-20 07:40

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('MedServ_app', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='productomedico',
            name='p_precio',
            field=models.IntegerField(),
        ),
    ]